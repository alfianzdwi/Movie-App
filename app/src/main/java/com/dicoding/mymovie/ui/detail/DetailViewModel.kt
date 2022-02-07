package com.dicoding.mymovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovie.data.source.FilmRepository
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.vo.Resource

class DetailViewModel (private val filmRepository: FilmRepository): ViewModel() {

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setFilm(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                detailTvShow = filmRepository.getDetailTvShow(id.toInt())
            }

            MOVIE -> {
                detailMovie = filmRepository.getDetailMovie(id.toInt())
            }
        }
    }

    fun getDetailTvShow() = detailTvShow

    fun getDetailMovie() = detailMovie


    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            filmRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            filmRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    companion object {
        const val TV_SHOW = "tvShow"
        const val MOVIE = "movie"
    }
}