package com.dicoding.mymovie.ui.favorite.favmovie

import androidx.lifecycle.ViewModel
import com.dicoding.mymovie.data.source.FilmRepository


class FavoriteMovieViewModel (private val repository: FilmRepository) : ViewModel(){
    fun getFavoriteMovies() = repository.getFavoriteMovies()
}
