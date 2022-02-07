package com.dicoding.mymovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovie.data.source.FilmRepository
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.vo.Resource

class MovieViewModel (private val filmRepository: FilmRepository): ViewModel() {
    fun getMovies(filter: String): LiveData<Resource<PagedList<MovieEntity>>> = filmRepository.getMovies(filter)
}