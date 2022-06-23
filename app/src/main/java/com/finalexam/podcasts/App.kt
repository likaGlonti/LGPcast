package com.finalexam.podcasts

import android.app.Application
import androidx.room.Room
import com.finalexam.podcasts.data.database.AppDatabase

class App : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}