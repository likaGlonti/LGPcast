package com.finalexam.podcasts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PodcastEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun calorieDao(): PodcastDao
}