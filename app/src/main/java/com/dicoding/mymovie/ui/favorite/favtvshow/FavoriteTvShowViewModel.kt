package com.dicoding.mymovie.ui.favorite.favtvshow

import androidx.lifecycle.ViewModel
import com.dicoding.mymovie.data.source.FilmRepository


class FavoriteTvShowViewModel(private val repository: FilmRepository): ViewModel() {
    fun getFavoriteTvShows() = repository.getFavoriteTvShows()
}