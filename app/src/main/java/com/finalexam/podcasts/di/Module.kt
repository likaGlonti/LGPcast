package com.finalexam.podcasts.di

import com.finalexam.podcasts.data.remote.ApiClient
import com.finalexam.podcasts.data.remote.PodcastsRepositoryImpl
import com.finalexam.podcasts.domain.PodcastsRepository

object Module {
    val repository: PodcastsRepository by lazy { PodcastsRepositoryImpl(ApiClient.getService) }
}