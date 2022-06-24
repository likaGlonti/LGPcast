package com.finalexam.podcasts.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: String,
    val audio: String,
    val title: String,
    val image: String,
    @SerialName("audio_length_sec") val audioLengthSec: Int
)