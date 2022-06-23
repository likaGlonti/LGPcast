package com.finalexam.podcasts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PodcastDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg podcasts: PodcastEntity)

    @Query("SELECT * FROM podcasts")
    suspend fun getAll(): List<PodcastEntity>
}