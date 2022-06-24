package com.finalexam.podcasts.presentation.entity

sealed interface BaseDashboardItem
data class PodcastPresentationItem(
    val id: String,
    val image: String,
    val titleOriginal: String,
)

data class Podcasts(val podcasts: List<PodcastPresentationItem>) : BaseDashboardItem

data class Title(val podcastTypeString: String) : BaseDashboardItem

data class GenresAdapterItem(val genres: List<GenrePresentationItem>) : BaseDashboardItem

