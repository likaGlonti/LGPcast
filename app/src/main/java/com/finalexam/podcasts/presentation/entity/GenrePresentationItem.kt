package com.finalexam.podcasts.presentation.entity

data class GenrePresentationItem(
    val id: Int,
    val name: String,
    val onClick: (() -> Unit)
)
