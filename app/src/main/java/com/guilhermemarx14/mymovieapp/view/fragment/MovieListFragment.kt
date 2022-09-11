package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieListBinding
import com.guilhermemarx14.mymovieapp.view.adapter.MovieRecyclerViewAdapter
import com.guilhermemarx14.mymovieapp.view.adapter.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.MovieListViewModel

class MovieListFragment : Fragment(), MovieSelectedListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieRecyclerViewAdapter
    private val movieListViewModel by hiltNavGraphViewModels<MovieListViewModel>(R.id.nav_graph)
    private val movieDetailsViewModel by hiltNavGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)
        setupRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        movieListViewModel.getMovieList()
    }

    private fun setupBinding(inflater: LayoutInflater){
        binding = FragmentMovieListBinding.inflate(inflater)
        recyclerView = binding.list
        binding.viewModel = movieListViewModel
        binding.lifecycleOwner = this

    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MovieRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.updateValues(it) }
        }

        movieListViewModel.navigateToDetailsLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
                findNavController().navigate(action)
            }
        }

    }

    override fun onItemSelected(position: Int) {
        movieListViewModel.movieListLiveData.value?.get(position)?.id?.let { id ->
            Log.d("movieApp", "Selected id - $id")
            movieDetailsViewModel.getMovie(id)
            movieListViewModel.navigateToDetails()
        }

    }

}