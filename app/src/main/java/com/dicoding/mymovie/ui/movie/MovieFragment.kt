package com.dicoding.mymovie.ui.movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovie.R
import com.dicoding.mymovie.databinding.FragmentMovieBinding
import com.dicoding.mymovie.utils.SortUtils.NEWEST
import com.dicoding.mymovie.utils.SortUtils.OLDEST
import com.dicoding.mymovie.utils.SortUtils.RANDOM
import com.dicoding.mymovie.viewmodel.ViewModelFactory
import com.dicoding.mymovie.vo.Status


class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()

            viewModel.getMovies(NEWEST).observe(viewLifecycleOwner, { movies ->
                if(movies != null){
                    when(movies.status){
                        Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
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

        viewModel.getMovies(sort).observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies.status){
                    Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentMovieBinding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                    }
                    Status.ERROR -> {
                        fragmentMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }


}