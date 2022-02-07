package com.dicoding.mymovie.ui.favorite.favtvshow

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovie.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.mymovie.viewmodel.ViewModelFactory
import com.dicoding.mytvShow.ui.tvshow.TvShowAdapter

class FavoriteTvShowFragment: Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            binding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            val mAdapter = TvShowAdapter()



            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->
                if(tvShows != null){
                    mAdapter.submitList(tvShows)
                    binding.progressBar.visibility = View.GONE
                }

                if (tvShows.isEmpty())
                    checkEmpty(true)
                else
                    checkEmpty(false)
            })

            with(binding.rvFavoriteTvShows){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mAdapter
            }
        }
    }

    private fun checkEmpty(isEmpty: Boolean) {
        if (isEmpty)
            binding.apply {
                rvFavoriteTvShows.visibility = View.GONE
                tvEmpty.visibility = View.VISIBLE
            }
        else
            binding.apply {
                rvFavoriteTvShows.visibility = View.VISIBLE
                tvEmpty.visibility = View.GONE
            }
    }

}