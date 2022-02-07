package com.dicoding.mytvShow.ui.tvshow

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovie.R
import com.dicoding.mymovie.databinding.FragmentTvShowBinding
import com.dicoding.mymovie.ui.tvshow.TvShowViewModel
import com.dicoding.mymovie.utils.SortUtils.NEWEST
import com.dicoding.mymovie.utils.SortUtils.OLDEST
import com.dicoding.mymovie.utils.SortUtils.RANDOM
import com.dicoding.mymovie.viewmodel.ViewModelFactory
import com.dicoding.mymovie.vo.Status


class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter()



            viewModel.getTvShows(NEWEST).observe(viewLifecycleOwner, { tvShows ->
                if(tvShows != null){
                    when(tvShows.status){
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data)
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShows){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sort,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest-> sort = NEWEST
            R.id.action_oldest -> sort = OLDEST
            R.id.action_random -> sort = RANDOM
        }

        viewModel.getTvShows(sort).observe(viewLifecycleOwner, { tvShows ->
            if(tvShows != null){
                when(tvShows.status){
                    Status.LOADING -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentTvShowBinding.progressBar.visibility = View.GONE
                        tvShowAdapter.submitList(tvShows.data)
                    }
                    Status.ERROR -> {
                        fragmentTvShowBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

}