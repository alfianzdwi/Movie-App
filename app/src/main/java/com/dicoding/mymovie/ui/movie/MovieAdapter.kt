package com.dicoding.mymovie.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovie.R
import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.databinding.ItemsFilmBinding
import com.dicoding.mymovie.ui.detail.DetailActivity
import com.dicoding.mymovie.ui.detail.DetailViewModel
import com.dicoding.mymovie.utils.DatetimeFormat
import com.dicoding.mymovie.utils.Url


class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            val changeFormat = DatetimeFormat()
            with(binding){
                tvItemTitle.text = movie.title
                tvRating.text = movie.rating.toString()
                tvItemRelease.text = changeFormat.changeFormatDate(movie.releaseDate)
                tvLanguage.text = movie.language

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILM, movie.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, DetailViewModel.MOVIE)
                    itemView.context.startActivity(intent)
                }


                Glide.with(itemView.context)
                    .load(Url.IMAGE_URL + movie.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)

            }
        }
    }
}