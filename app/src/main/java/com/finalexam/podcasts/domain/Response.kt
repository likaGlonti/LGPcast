package com.finalexam.podcasts.domain


sealed class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val error:ErrorEntity)

}
