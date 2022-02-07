package com.dicoding.mymovie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovie.data.source.FilmRepository
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.vo.Resource


class TvShowViewModel (private val filmRepository: FilmRepository): ViewModel() {
    fun getTvShows(filter: String): LiveData<Resource<PagedList<TvShowEntity>>> = filmRepository.getTvShows(filter)
}