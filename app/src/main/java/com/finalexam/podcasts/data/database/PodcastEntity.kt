package com.finalexam.podcasts.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcasts")
data class PodcastEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "podcast_id") val podcastId: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "title_original") val titleOriginal: String,
    @ColumnInfo(name = "podcast_type") val podcastType: String
)
