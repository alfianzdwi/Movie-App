package com.dicoding.mymovie.network

import com.dicoding.mymovie.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.mymovie.data.source.remote.response.movie.MoviesResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.DetailTvShowResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowsResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<DetailTvShowResponse>
}