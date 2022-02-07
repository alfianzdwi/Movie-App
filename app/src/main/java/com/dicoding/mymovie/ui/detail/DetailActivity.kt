package com.dicoding.mymovie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovie.R
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.databinding.ActivityDetailBinding
import com.dicoding.mymovie.databinding.ContentDetailBinding
import com.dicoding.mymovie.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.mymovie.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.dicoding.mymovie.utils.Url.IMAGE_URL
import com.dicoding.mymovie.viewmodel.ViewModelFactory
import com.dicoding.mymovie.vo.Status

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailFilmsBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailFilmsBinding.detailContent
        setContentView(activityDetailFilmsBinding.root)

        setSupportActionBar(activityDetailFilmsBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showProgressBar(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        detailContentBinding.progressBar.visibility = View.VISIBLE
        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            dataCategory = extras.getString(EXTRA_CATEGORY)
            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory.toString())
                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    populateFilm(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)

                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else if (dataCategory == TV_SHOW) {
                    viewModel.getDetailTvShow().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    populateFilm(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }
    }


    @JvmName("populateMovie")
    private fun populateFilm(filmEntity: MovieEntity) {

        val hours: Int = filmEntity.runtime / 60
        val minutes: Int = filmEntity.runtime % 60
        val duration = resources.getString(R.string.duration_text, hours, minutes)

        with(detailContentBinding) {
            textGenre.text = filmEntity.genres
            textDuration.text = duration
            textTitle.text = filmEntity.title
            textDescription.text = filmEntity.overview
            textRating.text = filmEntity.rating.toString()
            textLanguage.text = filmEntity.language
            textRelease.text = filmEntity.releaseDate
        }

        Glide.with(this)
            .load(IMAGE_URL + filmEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        showProgressBar(false)
    }


    @JvmName("populateTvShow")
    private fun populateFilm(filmEntity: TvShowEntity) {

        val hours: Int = filmEntity.runtime / 60
        val minutes: Int = filmEntity.runtime % 60
        val duration = resources.getString(R.string.duration_text, hours, minutes)

        with(detailContentBinding) {
            textGenre.text = filmEntity.genres
            textDuration.text = duration
            textTitle.text = filmEntity.title
            textDescription.text = filmEntity.overview
            textRating.text = filmEntity.rating.toString()
            textLanguage.text = filmEntity.language
            textRelease.text = filmEntity.releaseDate
        }

        Glide.with(this)
            .load(IMAGE_URL + filmEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        showProgressBar(false)
    }


    private fun showProgressBar(state: Boolean) {
        detailContentBinding.progressBar.isVisible = state
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_unfavorite_24)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (dataCategory == MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.isFavorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (dataCategory == TV_SHOW) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.isFavorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            if (dataCategory == MOVIE) {
                viewModel.setFavoriteMovie()
            } else if (dataCategory == TV_SHOW) {
                viewModel.setFavoriteTvShow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }
}