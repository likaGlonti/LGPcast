package com.finalexam.podcasts.presentation.best

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import com.finalexam.podcasts.presentation.entity.GenrePresentationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BestPodcastsDashboardViewModel(repository: PodcastsRepository) : ViewModel() {


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
    }
}