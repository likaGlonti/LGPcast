package com.finalexam.podcasts.presentation.best

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.finalexam.podcasts.App
import com.finalexam.podcasts.data.database.PodcastEntity
import com.finalexam.podcasts.data.worker.PodcastsWorker
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import com.finalexam.podcasts.presentation.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BestPodcastsDashboardViewModel(repository: PodcastsRepository, context: Context) :
    ViewModel() {

    private val worker = WorkManager.getInstance(context)
    private val podcastsWorker = OneTimeWorkRequestBuilder<PodcastsWorker>()
        .build()
    val workInfo: LiveData<WorkInfo> = worker.getWorkInfoByIdLiveData(podcastsWorker.id)

    private val genres = MutableStateFlow<List<GenrePresentationItem>>(emptyList())


    private val _dashboardPodcasts = MutableStateFlow<List<BaseDashboardItem>>(emptyList())
    val dashboardPodcasts: StateFlow<List<BaseDashboardItem>> = _dashboardPodcasts

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
            worker.enqueue(podcastsWorker)
        }
    }

    fun getData() {
        viewModelScope.launch {
            val podcasts = arrayListOf<BaseDashboardItem>()
            App.instance.db.calorieDao().getAll().apply {
                this.groupBy { it.podcastType }.forEach {
                    podcasts.add(GenresAdapterItem(genres.value))
                    podcasts.add(Title(it.key))
                    podcasts.add(Podcasts(it.value.map { list -> list.toPodcast() }))
                }
            }

            _dashboardPodcasts.emit(podcasts)
        }
    }

    private fun PodcastEntity.toPodcast() =
        PodcastPresentationItem(
            id = podcastId,
            image = image,
            titleOriginal = titleOriginal
        )
}