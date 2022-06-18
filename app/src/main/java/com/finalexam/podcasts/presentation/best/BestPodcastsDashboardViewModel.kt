package com.finalexam.podcasts.presentation.best

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BestPodcastsDashboardViewModel(repository: PodcastsRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.genres()) {
                is Response.Success -> {
                    response.data.genres.forEach {
                        it
                    }
                }
                is Response.Error -> {
                    println(response.error.message)
                }
            }
        }
    }
}