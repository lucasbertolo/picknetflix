package com.example.netflixrandom.data.api

import com.example.netflixrandom.data.model.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("content/random")
    suspend fun getRandomMovie(
        @Query("minimum_imdb") imdbRate: Int?,
        @Query("genre") genre: Int?,
        @Query("content_kind") kind: String?,
        @Query("sources") sources: String
    ): Movie
}

class MovieApiInstance {
    companion object {
        private const val BASE_URL = "https://api.reelgood.com/v3.0/"

        private val movieService: Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        }

        val api: MovieApi by lazy {
            movieService.create(MovieApi::class.java)
        }
    }
}

