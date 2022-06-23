package com.finalexam.podcasts.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.finalexam.podcasts.App
import com.finalexam.podcasts.data.database.PodcastEntity
import com.finalexam.podcasts.data.models.Podcast
import com.finalexam.podcasts.di.Module
import com.finalexam.podcasts.domain.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PodcastsWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            val names = arrayListOf(
                "star wars",
                "hunger games",
                "harry potter",
                "stranger things",
                "game of thrones"
            )

            val dao = App.instance.db.calorieDao()
            names.forEach {
                when (val podcasts = Module.repository.podcastsByName(it)) {
                    is Response.Success<List<Podcast>> -> {
                        dao.insertAll(*podcasts.data.map { podcast ->
                            PodcastEntity(
                                podcastId = podcast.id,
                                image = podcast.image,
                                titleOriginal = podcast.titleOriginal, podcastType = it
                            )
                        }.toTypedArray())
                    }
                    is Response.Error -> throw Exception(podcasts.error)
                }
            }
            Result.success()
        } catch (e: Throwable) {
            Result.failure()
        }
    }
}