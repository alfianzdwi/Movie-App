package com.dicoding.mytvShow.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovie.R
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.databinding.ItemsFilmBinding
import com.dicoding.mymovie.ui.detail.DetailActivity
import com.dicoding.mymovie.ui.detail.DetailViewModel
import com.dicoding.mymovie.utils.DatetimeFormat
import com.dicoding.mymovie.utils.Url


class TvShowAdapter: PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemsFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemsFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            val changeFormat = DatetimeFormat()
            with(binding){
                tvItemTitle.text = tvShow.title
                tvRating.text = tvShow.rating.toString()
                tvItemRelease.text = changeFormat.changeFormatDate(tvShow.releaseDate)
                tvLanguage.text = tvShow.language

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILM, tvShow.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, DetailViewModel.TV_SHOW)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(Url.IMAGE_URL + tvShow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)

            }
        }
    }
}