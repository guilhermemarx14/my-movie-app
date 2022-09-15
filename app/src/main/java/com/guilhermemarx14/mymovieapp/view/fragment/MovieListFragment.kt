package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemarx14.mymovieapp.MainActivity
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieListBinding
import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.view.adapter.MovieRecyclerViewAdapter
import com.guilhermemarx14.mymovieapp.view.adapter.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.MovieListViewModel

class MovieListFragment : Fragment(), MovieSelectedListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieRecyclerViewAdapter
    private lateinit var hostActivity: MainActivity
    private val movieListViewModel by hiltNavGraphViewModels<MovieListViewModel>(R.id.nav_graph)
    private val movieDetailsViewModel by hiltNavGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph)
    private val args: MovieListFragmentArgs by navArgs()

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
        movieListViewModel.getMovieList(args.listType)
    }

    private fun setupBinding(inflater: LayoutInflater){
        binding = FragmentMovieListBinding.inflate(inflater)
        recyclerView = binding.list
        binding.viewModel = movieListViewModel
        hostActivity = activity as MainActivity
        binding.lifecycleOwner = this

        val title = when(args.listType){
            MovieListType.NOW_PLAYING -> resources.getString(R.string.now_playing_title)
            MovieListType.UPCOMING -> resources.getString(R.string.upcoming_title)
            MovieListType.POPULAR -> resources.getString(R.string.popular_title)
            MovieListType.TOP_RATED -> resources.getString(R.string.top_rated_title)
        }
        hostActivity.titleTextView.text = resources.getString(R.string.list_title, title)

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
        /*movieListViewModel.movieListLiveData.value?.get(position)?.overview?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_INDEFINITE)
            .setTextMaxLines(5)
            .setAction(R.string.close){ }
            .show()
        }*/
    }

    override fun onDetailsSelected(position: Int) {
        movieListViewModel.movieListLiveData.value?.get(position)?.id?.let { id ->
            Log.d("movieApp", "Selected id - $id")
            movieDetailsViewModel.getMovie(id)
            movieListViewModel.navigateToDetails()
        }

    }

}