package com.finalexam.podcasts.presentation.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finalexam.podcasts.domain.PodcastsRepository

class PodcastPlayListViewModelFactory(private val repository: PodcastsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PodcastsRepository::class.java).newInstance(repository)
    }
}