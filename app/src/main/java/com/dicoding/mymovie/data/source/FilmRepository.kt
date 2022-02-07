package com.dicoding.mymovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mymovie.data.source.local.LocalDataSource
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.data.source.remote.response.ApiResponse
import com.dicoding.mymovie.data.source.remote.response.RemoteDataSource
import com.dicoding.mymovie.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.mymovie.data.source.remote.response.movie.MovieResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.DetailTvShowResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.mymovie.utils.AppExecutors
import com.dicoding.mymovie.vo.Resource
import java.lang.StringBuilder

class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getMovies(filter: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(filter), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovies()

            public override fun saveCallResult(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(response.id,
                        response.title,
                        response.posterPath,
                        "",
                        false,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                        response.originalLanguage,
                        0,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(response: DetailMovieResponse) {
                val genres = StringBuilder().append("")

                for (i in response.genres.indices) {
                    if (i < response.genres.size - 1) {
                        genres.append("${response.genres[i].name}, ")
                    } else {
                        genres.append(response.genres[i].name)
                    }
                }

                val movie = MovieEntity(
                    response.id,
                    response.title,
                    response.posterPath,
                    genres.toString(),
                    false,
                    response.releaseDate,
                    response.voteAverage,
                    response.overview,
                    response.originalLanguage,
                    response.runtime,
                )

                localDataSource.updateMovie(movie, false)
            }


        }.asLiveData()
    }

    override fun getTvShows(filter: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShows(filter), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(tvShowResponse: List<TvShowResponse>) {
                val movieList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val movie = TvShowEntity(response.id,
                        response.name,
                        response.posterPath,
                        "",
                        false,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                        response.originalLanguage,
                        0,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall():LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(response: DetailTvShowResponse) {
                val genres = StringBuilder().append("")

                for (i in response.genres.indices) {
                    if (i < response.genres.size - 1) {
                        genres.append("${response.genres[i].name}, ")
                    } else {
                        genres.append(response.genres[i].name)
                    }
                }

                val tvShow = TvShowEntity(
                    response.id,
                    response.name,
                    response.posterPath,
                    genres.toString(),
                    false,
                    response.firstAirDate,
                    response.voteAverage,
                    response.overview,
                    response.originalLanguage,
                    response.episodeRunTime.average().toInt(),
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }
}