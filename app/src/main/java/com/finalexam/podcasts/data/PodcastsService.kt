package com.finalexam.podcasts.data

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.data.models.TypeaHeadPodcasts
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastsService {

    @GET("/best_podcasts")
    fun getBestPodcasts()

    @GET("genres")
    suspend fun getGenres(): Genres

    @GET("typeahead")
    suspend fun typeaHead(@Query("q") name: String): TypeaHeadPodcasts
}