package com.finalexam.podcasts.domain

import com.finalexam.podcasts.data.models.Genres

interface PodcastsRepository {
    suspend fun genres(): Response<Genres>
}