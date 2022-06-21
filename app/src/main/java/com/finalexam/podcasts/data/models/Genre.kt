package com.finalexam.podcasts.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Genre(
    val id: Int,
    val name: String,
    @SerialName("parent_id") val parentId: Int = 0
)
