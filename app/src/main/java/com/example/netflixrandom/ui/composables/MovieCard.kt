package com.example.netflixrandom.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.netflixrandom.data.model.Movie

@Composable
fun MovieCard(movie: Movie) {
    val type = if (movie.content_type == "m") "movie" else "show"
    val url =
        "https://img.rgstatic.com/content/${type}/${movie.id}/poster-500.jpg"

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray, //Card background color
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        Column(
            modifier = Modifier.padding(all = 12.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = movie.slug,
                modifier = Modifier.aspectRatio(1f)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Title(text = movie.title)
            Spacer(modifier = Modifier.size(6.dp))
            InfoRow(movie)
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = movie.overview,
                style = TextStyle(color = Color.LightGray, fontSize = 12.sp),
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))

        }


    }


}

@Composable
fun InfoRow(movie: Movie) {
    Row {
        CardInfoText(text = movie.released_on.substring(0, 4))
        CardInfoText(text = "IMDB: ${movie.imdb_rating}/10")
        CardInfoText(text = "${movie.runtime}m")
    }
}


@Composable
fun CardInfoText(text: String) {
    Text(
        text = text,
        style = TextStyle(color = Color.LightGray, fontSize = 12.sp),
        modifier = Modifier.padding(horizontal = 6.dp)
    )
}