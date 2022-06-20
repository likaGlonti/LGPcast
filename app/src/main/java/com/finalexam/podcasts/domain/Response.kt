package com.finalexam.podcasts.domain

import java.lang.Exception


sealed class Response<T>{
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val error:Exception):Response<T>()

}
