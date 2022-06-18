package com.finalexam.podcasts.domain

sealed class ErrorEntity {
    object ServerError : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object Unknown : ErrorEntity()
}
