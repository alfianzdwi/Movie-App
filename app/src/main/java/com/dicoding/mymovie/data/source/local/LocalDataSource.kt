package com.dicoding.mymovie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.data.source.local.room.FilmDao
import com.dicoding.mymovie.utils.SortUtils
import com.dicoding.mymovie.utils.SortUtils.MOVIE_ENTITIES
import com.dicoding.mymovie.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource private constructor(private val mFilmDao : FilmDao){
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(filmDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        mFilmDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getMovieById(id: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mFilmDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun getTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> =
        mFilmDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(id)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }
}