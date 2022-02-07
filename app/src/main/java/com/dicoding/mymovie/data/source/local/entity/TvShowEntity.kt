package com.dicoding.mymovie.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "runtime")
    var runtime: Int,
)