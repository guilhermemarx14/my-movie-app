package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieListBinding
import com.guilhermemarx14.mymovieapp.interfaces.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.view.adapter.MovieRecyclerViewAdapter
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel

class MovieListFragment : Fragment(), MovieSelectedListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieRecyclerViewAdapter
    private val viewModel by navGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)
        setupRecyclerView()
        lifecycle.addObserver(viewModel)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovieList()
    }

    private fun setupBinding(inflater: LayoutInflater){
        binding = FragmentMovieListBinding.inflate(inflater)
        recyclerView = binding.list
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MovieRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.updateValues(it) }
        }

        viewModel.navigateToDetailsLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
                findNavController().navigate(action)
            }
        }

    }

    override fun onItemSelected(position: Int) {
            viewModel.onMovieSelected(position)
    }

}