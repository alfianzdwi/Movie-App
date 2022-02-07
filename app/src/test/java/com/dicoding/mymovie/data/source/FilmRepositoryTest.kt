package com.dicoding.mymovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.academy.utils.PagedListUtil
import com.dicoding.mymovie.data.source.local.LocalDataSource
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.data.source.remote.response.RemoteDataSource
import com.dicoding.mymovie.utils.AppExecutors
import com.dicoding.mymovie.utils.DataDummy
import com.dicoding.mymovie.utils.LiveDataTestUtil
import com.dicoding.mymovie.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class FilmRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.generateDummyRemoteMovies()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.generateDummyRemoteDetailMovie()

    private val tvShowResponse = DataDummy.generateDummyRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.generateDummyRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies("NEWEST")).thenReturn(dataSourceFactory)
        filmRepository.getMovies("NEWEST")

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies("NEWEST")
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.generateDummyDetailMovie()
        `when`(local.getMovieById(movieId.toInt())).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(filmRepository.getDetailMovie(movieId.toInt()))
        verify(local).getMovieById(movieId.toInt())
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }


    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows("NEWEST")).thenReturn(dataSourceFactory)
        filmRepository.getTvShows("NEWEST")

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTvShows("NEWEST")
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.generateDummyDetailTvShow()
        `when`(local.getTvShowById(tvShowId.toInt())).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(filmRepository.getDetailTvShow(tvShowId.toInt()))
        verify(local).getTvShowById(tvShowId.toInt())
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

}