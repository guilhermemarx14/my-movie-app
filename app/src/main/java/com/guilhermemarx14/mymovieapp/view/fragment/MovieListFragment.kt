package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermemarx14.mymovieapp.MainActivity
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieListBinding
import com.guilhermemarx14.mymovieapp.lifecycle_observer.FragmentObserver
import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.view.adapter.MovieRecyclerViewAdapter
import com.guilhermemarx14.mymovieapp.view.adapter.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.viewmodel.BaseListViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.listViewModels.NowPlayingMovieListViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.listViewModels.PopularMovieListViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.listViewModels.TopRatedMovieListViewModel
import com.guilhermemarx14.mymovieapp.viewmodel.listViewModels.UpcomingMovieListViewModel

class MovieListFragment(val type: MovieListType) : Fragment(), MovieSelectedListener {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieRecyclerViewAdapter
    private lateinit var hostActivity: MainActivity
    private val topRatedMovieListViewModel by hiltNavGraphViewModels<TopRatedMovieListViewModel>(R.id.nav_graph)
    private val popularMovieListViewModel by hiltNavGraphViewModels<PopularMovieListViewModel>(R.id.nav_graph)
    private val nowPlayingMovieListViewModel by hiltNavGraphViewModels<NowPlayingMovieListViewModel>(
        R.id.nav_graph
    )
    private val upcomingMovieListViewModel by hiltNavGraphViewModels<UpcomingMovieListViewModel>(R.id.nav_graph)
    private val movieDetailsViewModel by hiltNavGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph)
    private lateinit var viewModel: BaseListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)
        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel = when (type) {
            MovieListType.TOP_RATED -> topRatedMovieListViewModel
            MovieListType.POPULAR -> popularMovieListViewModel
            MovieListType.NOW_PLAYING -> nowPlayingMovieListViewModel
            MovieListType.UPCOMING -> upcomingMovieListViewModel
        }

        binding.viewModel = viewModel
        setupObservers()

        arguments?.takeIf { it.containsKey("type") }?.apply {
            val textView: TextView = view.findViewById(android.R.id.text1)
            textView.text = "teste"
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.getMovieList()

    }

    private fun setupBinding(inflater: LayoutInflater) {


        binding = FragmentMovieListBinding.inflate(inflater)
        recyclerView = binding.list

        hostActivity = activity as MainActivity
        binding.lifecycleOwner = this


        val title = when (type) {
            MovieListType.NOW_PLAYING -> resources.getString(R.string.now_playing_title)
            MovieListType.UPCOMING -> resources.getString(R.string.upcoming_title)
            MovieListType.POPULAR -> resources.getString(R.string.popular_title)
            MovieListType.TOP_RATED -> resources.getString(R.string.top_rated_title)
        }
        hostActivity.titleTextView.text = resources.getString(R.string.list_title, title)

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MovieRecyclerViewAdapter(this)
        recyclerView.adapter = adapter

    }

    private fun setupObservers() {

        lifecycle.addObserver(FragmentObserver(type))

        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.updateValues(it) }
        }

        viewModel.navigateToDetailsLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                val action =
                    MainScreenFragmentDirections.actionMainScreenFragmentToMovieDetailFragment()
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
        viewModel.movieListLiveData.value?.get(position)?.id?.let { id ->
            Log.d("movieApp", "Selected id - $id")
            movieDetailsViewModel.getMovie(id)
            viewModel.navigateToDetails()
        }

    }

}