package com.dicoding.mymovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academy.utils.LiveDataTestUtil
import com.dicoding.mymovie.data.source.FilmRepository
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.mymovie.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.dicoding.mymovie.utils.DataDummy
import com.dicoding.mymovie.vo.Resource
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyDetailMovie()
    private val dummyTvShow = DataDummy.generateDummyDetailTvShow()

    private val movieId = dummyMovie.id.toString()
    private val tvShowId = dummyTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(filmRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(filmRepository.getDetailMovie(movieId.toInt())).thenReturn(movie)
        viewModel.setFilm(movieId, MOVIE)
        val detailMovieEntity = LiveDataTestUtil.getValue(viewModel.getDetailMovie())

        assertNotNull(movie)
        assertEquals(dummyMovie.id, detailMovieEntity.data?.id)
        assertEquals(dummyMovie.title, detailMovieEntity.data?.title)
        assertEquals(dummyMovie.overview, detailMovieEntity.data?.overview)
        assertEquals(dummyMovie.rating, detailMovieEntity.data?.rating)
        assertEquals(dummyMovie.isFavorite, detailMovieEntity.data?.isFavorite)
        assertEquals(dummyMovie.releaseDate, detailMovieEntity.data?.releaseDate)
        assertEquals(dummyMovie.genres, detailMovieEntity.data?.genres)
        assertEquals(dummyMovie.runtime, detailMovieEntity.data?.runtime)
        assertEquals(dummyMovie.language, detailMovieEntity.data?.language)


        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.generateDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(filmRepository.getDetailMovie(movieId.toInt())).thenReturn(movie)
        viewModel.setFilm(movieId, MOVIE)
        viewModel.setFavoriteMovie()
        val detailMovieEntity = LiveDataTestUtil.getValue(viewModel.getDetailMovie())

        assertNotNull(movie)
        assertEquals(dummyMovie.id, detailMovieEntity.data?.id)
        assertEquals(dummyMovie.title, detailMovieEntity.data?.title)
        assertEquals(dummyMovie.overview, detailMovieEntity.data?.overview)
        assertEquals(dummyMovie.rating, detailMovieEntity.data?.rating)
        assertEquals(dummyMovie.isFavorite, detailMovieEntity.data?.isFavorite)
        assertEquals(dummyMovie.releaseDate, detailMovieEntity.data?.releaseDate)
        assertEquals(dummyMovie.genres, detailMovieEntity.data?.genres)
        assertEquals(dummyMovie.runtime, detailMovieEntity.data?.runtime)
        assertEquals(dummyMovie.language, detailMovieEntity.data?.language)

        verify(filmRepository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.generateDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(filmRepository.getDetailTvShow(tvShowId.toInt())).thenReturn(tvShow)
        viewModel.setFilm(tvShowId, TV_SHOW)
        val detailTvShowEntity = LiveDataTestUtil.getValue(viewModel.getDetailTvShow())

        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, detailTvShowEntity.data?.id)
        assertEquals(dummyTvShow.title, detailTvShowEntity.data?.title)
        assertEquals(dummyTvShow.overview, detailTvShowEntity.data?.overview)
        assertEquals(dummyTvShow.rating, detailTvShowEntity.data?.rating)
        assertEquals(dummyTvShow.releaseDate, detailTvShowEntity.data?.releaseDate)
        assertEquals(dummyTvShow.genres, detailTvShowEntity.data?.genres)
        assertEquals(dummyTvShow.runtime, detailTvShowEntity.data?.runtime)
        assertEquals(dummyTvShow.language, detailTvShowEntity.data?.language)



        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.generateDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(filmRepository.getDetailTvShow(tvShowId.toInt())).thenReturn(tvShow)
        viewModel.setFilm(tvShowId, TV_SHOW)
        viewModel.setFavoriteTvShow()
        val detailTvShowEntity = LiveDataTestUtil.getValue(viewModel.getDetailTvShow())

        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, detailTvShowEntity.data?.id)
        assertEquals(dummyTvShow.title, detailTvShowEntity.data?.title)
        assertEquals(dummyTvShow.overview, detailTvShowEntity.data?.overview)
        assertEquals(dummyTvShow.rating, detailTvShowEntity.data?.rating)
        assertEquals(dummyTvShow.isFavorite, detailTvShowEntity.data?.isFavorite)
        assertEquals(dummyTvShow.releaseDate, detailTvShowEntity.data?.releaseDate)
        assertEquals(dummyTvShow.genres, detailTvShowEntity.data?.genres)
        assertEquals(dummyTvShow.runtime, detailTvShowEntity.data?.runtime)
        assertEquals(dummyTvShow.language, detailTvShowEntity.data?.language)



        verify(filmRepository).setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}