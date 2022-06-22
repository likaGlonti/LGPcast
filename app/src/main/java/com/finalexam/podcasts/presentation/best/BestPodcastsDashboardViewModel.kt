package com.finalexam.podcasts.presentation.best

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.finalexam.podcasts.data.worker.PodcastsWorker
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import com.finalexam.podcasts.presentation.entity.GenrePresentationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BestPodcastsDashboardViewModel(repository: PodcastsRepository, context: Context) :
    ViewModel() {

    private val worker = WorkManager.getInstance(context)

    val genres = MutableStateFlow<List<GenrePresentationItem>>(emptyList())

    val onGenreClick = MutableSharedFlow<Int>(replay = 1)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.genres()) {
                is Response.Success -> {
                    genres.emit(response.data.genres.take(20).map {
                        GenrePresentationItem(
                            it.id,
                            name = it.name,
                            onClick = {
                                onGenreClick.tryEmit(it.id)
                            }
                        )
                    })
                }
                is Response.Error -> {

                }
            }
        }
        getPodcasts()
    }

    private fun getPodcasts() {
        viewModelScope.launch(Dispatchers.IO) {
            val podcastsWorker = OneTimeWorkRequestBuilder<PodcastsWorker>()
                .build()
            worker.enqueue(podcastsWorker)
        }
    }
}