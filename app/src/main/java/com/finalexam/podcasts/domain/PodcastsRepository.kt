package com.finalexam.podcasts.domain

import com.finalexam.podcasts.data.models.Genres

interface PodcastsRepository {

    fun genres(): Response<Genres>
}