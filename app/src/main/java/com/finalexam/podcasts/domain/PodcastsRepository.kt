package com.finalexam.podcasts.domain

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.data.models.Podcast
import com.finalexam.podcasts.data.models.PodcastById

interface PodcastsRepository {
    suspend fun genres(): Response<Genres>
    suspend fun podcastsByName(name:String):Response<List<Podcast>>
    suspend fun getPodcastById(id:String):Response<PodcastById>
}