package com.finalexam.podcasts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PodcastDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg podcasts: PodcastEntity)
}