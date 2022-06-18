package com.finalexam.podcasts.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiClient {
    private val json = Json {
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
    private val contentType = "application/json".toMediaType()

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("X-ListenAPI-Key", "cf69dc6fa0024866ab39bc898eaed9a8").build()
            it.proceed(request)
        }
        .build()


    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://listen-api.listennotes.com/api/v2/")
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
    val getService by lazy<PodcastsService> {
        getRetrofit().create(PodcastsService::class.java)
    }
}