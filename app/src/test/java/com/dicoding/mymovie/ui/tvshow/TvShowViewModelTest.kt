package com.dicoding.mytvShow.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovie.data.source.FilmRepository

import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.ui.tvshow.TvShowViewModel
import com.dicoding.mymovie.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        `when`(filmRepository.getTvShows("NEWEST")).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows("NEWEST").value?.data
        verify(filmRepository).getTvShows("NEWEST")
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShows("NEWEST").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
    
    
}