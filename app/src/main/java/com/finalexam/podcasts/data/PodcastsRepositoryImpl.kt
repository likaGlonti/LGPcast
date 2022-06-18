package com.finalexam.podcasts.data

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response

class PodcastsRepositoryImpl(private val service: PodcastsService) : PodcastsRepository {
    override fun genres(): Response<Genres> {
        TODO("Not yet implemented")
    }
}