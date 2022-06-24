package com.finalexam.podcasts.presentation.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalexam.podcasts.data.models.Episode
import com.finalexam.podcasts.data.models.PodcastById
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import com.finalexam.podcasts.presentation.entity.EpisodePresentationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class PodcastPlayListViewModel(private val repository: PodcastsRepository) : ViewModel() {

    private val _episodes = MutableStateFlow<List<EpisodePresentationItem>>(emptyList())
    val episodes: StateFlow<List<EpisodePresentationItem>> = _episodes

    fun getPodcastsEpisodes(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val podcast = repository.getPodcastById(id)) {
                is Response.Success<PodcastById> -> _episodes.emit(podcast.data.episodes.map { it.toPresentation() })
                is Response.Error -> throw Exception(podcast.error.message)
            }
        }
    }

    private fun Episode.toPresentation() =
        EpisodePresentationItem(id, audio, title, image, audioLengthSec)
}