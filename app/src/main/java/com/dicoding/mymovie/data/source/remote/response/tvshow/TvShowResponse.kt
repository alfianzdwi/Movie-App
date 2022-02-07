package com.dicoding.mymovie.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

)
