package com.finalexam.podcasts.di

import com.finalexam.podcasts.data.ApiClient
import com.finalexam.podcasts.data.PodcastsRepositoryImpl
import com.finalexam.podcasts.domain.PodcastsRepository

object Module {
    val repository: PodcastsRepository by lazy { PodcastsRepositoryImpl(ApiClient.getService) }
}