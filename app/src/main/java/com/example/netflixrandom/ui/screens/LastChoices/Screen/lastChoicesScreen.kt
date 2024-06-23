package com.example.netflixrandom.ui.screens.LastChoices.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.netflixrandom.ui.composables.MovieCard
import com.example.netflixrandom.ui.composables.Title
import com.example.netflixrandom.ui.screens.HomeScreen.ViewModel.HomeViewModel

@Composable
fun LastChoicesScreen(
    viewModel: HomeViewModel,
) {
    viewModel.clearMovie()

    val movies = viewModel.movieState.value.moviesList
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Title(text = "Last Movies Choices")

        if (movies.isEmpty()) {
            Spacer(modifier = Modifier.size(64.dp))
            Text(text = "Ops, it seems that you haven't pick any movies yet...", color = Color.White)
            return
        }

        LazyVerticalGrid(
            GridCells.Fixed(1), contentPadding = PaddingValues(all = 12.dp), modifier = Modifier
                .fillMaxSize()
        ) {
            items(movies.size) { movie ->
                MovieCard(movie = movies[movie])
            }
        }
    }
}





