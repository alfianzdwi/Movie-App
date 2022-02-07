package com.dicoding.mymovie.ui.favorite.favmovie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovie.databinding.FragmentFavoriteMovieBinding
import com.dicoding.mymovie.ui.movie.MovieAdapter
import com.dicoding.mymovie.viewmodel.ViewModelFactory

class FavoriteMovieFragment: Fragment() {
    private lateinit var binding: FragmentFavoriteMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            binding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            val mAdapter = MovieAdapter()

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                if(movies != null){
                    mAdapter.submitList(movies)
                    binding.progressBar.visibility = View.GONE
                }

                if (movies.isEmpty())
                    checkEmpty(true)
                else
                    checkEmpty(false)
            })

            with(binding.rvFavoriteMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mAdapter
            }
        }
    }

    private fun checkEmpty(isEmpty: Boolean) {
        if (isEmpty)
            binding.apply {
                rvFavoriteMovie.visibility = View.GONE
                tvEmpty.visibility = View.VISIBLE
            }
        else
            binding.apply {
                rvFavoriteMovie.visibility = View.VISIBLE
                tvEmpty.visibility = View.GONE
            }
    }
}