package com.finalexam.podcasts.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Podcast(
    val id: String,
    val image: String,
    @SerialName("title_original") val titleOriginal: String
)