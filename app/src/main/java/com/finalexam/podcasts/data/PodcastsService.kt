package com.finalexam.podcasts.data

import com.finalexam.podcasts.data.models.Genres
import retrofit2.http.GET

interface PodcastsService {

    @GET("/best_podcasts")
    fun getBestPodcasts()

    @GET("genres")
    suspend fun getGenres(): Genres
}