package com.dicoding.mymovie.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int,
)