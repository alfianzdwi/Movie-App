package com.dicoding.mymovie.di

import android.content.Context
import com.dicoding.mymovie.data.source.FilmRepository
import com.dicoding.mymovie.data.source.local.LocalDataSource
import com.dicoding.mymovie.data.source.local.room.FilmDatabase
import com.dicoding.mymovie.data.source.remote.response.RemoteDataSource
import com.dicoding.mymovie.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FilmRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val appExecutors = AppExecutors()

        val database = FilmDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.filmDao())

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}