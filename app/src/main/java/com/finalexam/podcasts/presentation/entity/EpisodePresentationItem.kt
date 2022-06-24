package com.finalexam.podcasts.presentation.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodePresentationItem(
    val id: String,
    val audio: String,
    val title: String,
    val image:String,
    val audioLength: Int
) :Parcelable