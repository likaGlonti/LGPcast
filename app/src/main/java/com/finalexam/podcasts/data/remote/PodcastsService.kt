package com.finalexam.podcasts.data.remote

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.data.models.PodcastById
import com.finalexam.podcasts.data.models.TypeaHeadPodcasts
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastsService {

    @GET("podcasts/{id}")
    fun getPodcastById(@Path("id") id: String): PodcastById

    @GET("genres")
    suspend fun getGenres(): Genres

    @GET("typeahead")
    suspend fun typeaHead(
        @Query("q") name: String,
        @Query("show_podcasts") showPodcasts: Int = 1,
        @Query("show_genres") showGenres: Int = 1,
    ): TypeaHeadPodcasts
}