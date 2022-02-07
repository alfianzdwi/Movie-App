package com.dicoding.mymovie.data.source.remote.response.movie
import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("imdb_id")
    val imdbId: String,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("revenue")
    val revenue: Int,

    @field:SerializedName("genres")
    val genres: List<Genre>,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("adult")
    val adult: Boolean,
)
