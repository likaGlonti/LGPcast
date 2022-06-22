package com.finalexam.podcasts.domain

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.data.models.Podcast

interface PodcastsRepository {
    suspend fun genres(): Response<Genres>
    suspend fun podcastsByName(name:String):Response<List<Podcast>>
}