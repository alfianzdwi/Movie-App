package com.dicoding.mymovie.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovie.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.mymovie.data.source.remote.response.movie.MovieResponse
import com.dicoding.mymovie.data.source.remote.response.movie.MoviesResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.DetailTvShowResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.TvShowsResponse
import com.dicoding.mymovie.network.ApiConfig
import com.dicoding.mymovie.utils.EspressoIdlingResource
import com.dicoding.mymovie.utils.Url.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getMovies() : LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovies(API_KEY)
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val result = response.body()?.results
                if (result != null) resultMovie.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovie
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                resultDetailMovie.value = ApiResponse.success(response.body() as DetailMovieResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailMovie onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovie
    }

    fun getTvShows() : LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        client.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val result = response.body()?.results
                if (result != null) resultTvShow.postValue(ApiResponse.success(result))
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShow
    }


    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShowDetail(tvShowId, API_KEY)
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                resultDetailTvShow.value = ApiResponse.success(response.body() as DetailTvShowResponse)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailTvShow
    }



    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }


}