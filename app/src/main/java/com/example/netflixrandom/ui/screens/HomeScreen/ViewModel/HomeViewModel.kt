package com.example.netflixrandom.ui.screens.HomeScreen.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflixrandom.data.api.MovieApiInstance
import com.example.netflixrandom.data.model.Movie
import com.example.netflixrandom.data.model.MovieGenre
import com.example.netflixrandom.ui.composables.DropdownValue
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel() : ViewModel() {
    private val _movieState = mutableStateOf(MovieState())
    private var _selectedGenre = mutableStateOf<Int?>(null)
    private val _selectedRate = mutableStateOf<Int?>(null)
    private val _showMovie = mutableStateOf(true)
    private val _showSeries = mutableStateOf(true)

    val movieState: State<MovieState> = _movieState
    val selectedGenreState: State<Int?> = _selectedGenre
    val selectedRate: State<Int?> = _selectedRate
    val showMovie: State<Boolean> = _showMovie
    val showSeries: State<Boolean> = _showSeries

    fun onGenreChange(genre: Int) {
        _selectedGenre.value = genre
    }

    fun onShowMovies(value: Boolean) {
        if (!value && !showSeries.value) return;
        _showMovie.value = value
    }

    fun onShowSeries(value: Boolean) {
        if (!value && !showMovie.value) return;
        _showSeries.value = value
    }

    private fun getKindValue(): String {
        if (showMovie.value && showSeries.value) return "both"

        if (showSeries.value) return "show"

        if (showMovie.value) return "movie"

        return "both"
    }

    fun onRateChange(rate: Int) {
        _selectedRate.value = rate
    }

    fun fetchRandomMovie() {
        viewModelScope.launch {
            try {
                _movieState.value = _movieState.value.copy(
                    loading = true,
                )

                val genre = if (selectedGenreState.value == 1) null else selectedGenreState.value

                val response = MovieApiInstance.api.getRandomMovie(
                    genre = genre,
                    kind = getKindValue(),
                    imdbRate = selectedRate.value,
                    sources = "netflix"
                )

                _movieState.value = _movieState.value.copy(
                    movie = response,
                    moviesList = movieState.value.moviesList.plus(response),
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _movieState.value = _movieState.value.copy(
                    loading = false,
                )
            }
        }
    }

    fun clearMovie() {
        _movieState.value = _movieState.value.copy(
            movie = null,
        )
    }


    fun getMovieGenresDropdown(): List<DropdownValue> {
        return MovieGenre.entries.toTypedArray().map { DropdownValue(it.name, it.value) }
    }

    fun getRateDropdown(): List<DropdownValue> {
        return listOf(
            DropdownValue(value = 6, label = "6 >"),
            DropdownValue(value = 7, label = "7 >"),
            DropdownValue(value = 8, label = "8 >"),
            DropdownValue(value = 9, label = "9 >")
        )
    }

    data class MovieState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val moviesList: List<Movie> = listOf(),
        val error: String? = null
    )
}