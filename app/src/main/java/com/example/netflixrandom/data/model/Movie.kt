package com.example.netflixrandom.data.model

import java.util.*

data class Movie(
    val id: String,
    val slug: String,
    val title: String,
    val overview: String,
    val tagline: String?,
    val classification: String?,
    val runtime: Int?,
    val released_on: String,
    val has_poster: Boolean,
    val poster_blur: String,
    val has_backdrop: Boolean,
    val backdrop_blur: String?,
    val imdb_rating: Double?,
    val rt_critics_rating: Double?,
    val reelgood_score: Double?,
    val genres: List<Int>,
    val tracking: Boolean,
    val watchlisted: Boolean,
    val seen: Boolean,
    val season_count: Int,
    val content_type: String,
)

enum class MovieGenre(val value: Int) {
    All(1),
    Action(5),
    Animation(6),
    Anime(7),
    Biography(8),
    Children(9),
    Comedy(10),
    Crime(11),
    Documentary(13),
    Fantasy(15),
    Horror(19),
    Romance(24),
    Sport(26),
    Thriller(28);

    companion object {
        fun fromValue(value: Int): MovieGenre? =
            entries.find { it.value == value }
    }
}

data class UserMovies(val movies: List<Movie>)
