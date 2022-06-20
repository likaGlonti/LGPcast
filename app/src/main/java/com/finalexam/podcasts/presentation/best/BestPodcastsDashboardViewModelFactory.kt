package com.finalexam.podcasts.presentation.best

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finalexam.podcasts.domain.PodcastsRepository

class BestPodcastsDashboardViewModelFactory(private val repository: PodcastsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PodcastsRepository::class.java).newInstance(repository)
    }
}