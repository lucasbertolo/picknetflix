package com.example.netflixrandom.ui.screens.HomeScreen.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netflixrandom.R
import com.example.netflixrandom.data.model.MovieGenre
import com.example.netflixrandom.ui.composables.Checkbox
import com.example.netflixrandom.ui.composables.Dropdown
import com.example.netflixrandom.ui.composables.MovieCard
import com.example.netflixrandom.ui.composables.Title
import com.example.netflixrandom.ui.screens.HomeScreen.ViewModel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
//    val homeViewModel: HomeView = viewModel()
    val viewState by homeViewModel.movieState

    Box(modifier = Modifier.fillMaxSize()) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            Title(text = stringResource(R.string.home_title))
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = stringResource(R.string.home_description),
                color = Color.White,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
            Dropdown(
                placeholder = "All Genres",
                label = "Genre",
                items = homeViewModel.getMovieGenresDropdown(),
                currentValue = if (homeViewModel.selectedGenreState.value != null) MovieGenre.fromValue(
                    homeViewModel.selectedGenreState.value!!
                )
                    .toString() else null,
                onChange = { homeViewModel.onGenreChange(it) })
            Dropdown(
                placeholder = "Any Score",
                label = "Imdb Rate",
                items = homeViewModel.getRateDropdown(),
                currentValue = homeViewModel.selectedRate.value?.toString(),
                onChange = { homeViewModel.onRateChange(it) })

            Spacer(modifier = Modifier.size(6.dp))
            Row {
                Checkbox(
                    isChecked = homeViewModel.showMovie.value,
                    label = "Movies",
                    onChange = { homeViewModel.onShowMovies(it) })
                Checkbox(
                    isChecked = homeViewModel.showSeries.value,
                    label = "TV Shows",
                    onChange = { homeViewModel.onShowSeries(it) })
            }
            Spacer(modifier = Modifier.size(12.dp))
            Button(enabled = !viewState.loading, onClick = {
                homeViewModel.fetchRandomMovie()
            }) {
                Text(text = "Spin")
            }

            Spacer(modifier = Modifier.size(12.dp))

            if (viewState.movie != null) {
                MovieCard(movie = viewState.movie!!)
            }
        }
    }
}






