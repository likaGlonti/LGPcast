package com.finalexam.podcasts.presentation.entity

sealed interface BasePodcast
data class PodcastPresentationItem(
    val id: String,
    val image: String,
    val titleOriginal: String,
)

data class Podcasts(val podcasts: List<PodcastPresentationItem>) : BasePodcast

data class Title(val podcastTypeString: String) : BasePodcast

