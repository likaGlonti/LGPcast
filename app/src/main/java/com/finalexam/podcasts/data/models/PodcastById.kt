package com.finalexam.podcasts.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PodcastById(val id: String, val episodes: List<Episode>)
