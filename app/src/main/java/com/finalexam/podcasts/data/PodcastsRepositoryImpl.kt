package com.finalexam.podcasts.data

import com.finalexam.podcasts.data.models.Genres
import com.finalexam.podcasts.data.models.Podcast
import com.finalexam.podcasts.domain.PodcastsRepository
import com.finalexam.podcasts.domain.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PodcastsRepositoryImpl(private val service: PodcastsService) : PodcastsRepository {
    override suspend fun genres(): Response<Genres> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Response.Success(service.getGenres())
            } catch (e: Exception) {
                Response.Error(e)
            }
        }

    override suspend fun podcastsByName(name: String): Response<List<Podcast>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Response.Success(service.typeaHead(name).podcasts)
            } catch (e: Exception) {
                Response.Error(e)
            }
        }
}