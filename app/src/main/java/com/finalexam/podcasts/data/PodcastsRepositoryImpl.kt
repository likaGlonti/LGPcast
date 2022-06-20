package com.finalexam.podcasts.data

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PodcastsRepositoryImpl(private val service: PodcastsService) : PodcastsRepository {
  override suspend fun genres(): Response<Genres> =
       withContext(Dispatchers.IO) {
           return@withContext try {
               Response.Success( service.getGenres())
           }catch (e:Exception){
               Response.Error(e)
           }
       }
}