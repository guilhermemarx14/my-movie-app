package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.interfaces.MovieSelectedListener
import com.guilhermemarx14.mymovieapp.view.fragment.placeholder.PlaceholderContent
import com.guilhermemarx14.mymovieapp.viewmodel.MovieViewModel

/**
 * A fragment representing a list of Items.
 */
class MovieListFragment : Fragment(), MovieSelectedListener {

    private var columnCount = 1
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.nav_graph){
        defaultViewModelProviderFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MovieRecyclerViewAdapter(PlaceholderContent.ITEMS, this@MovieListFragment)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onItemSelected(position: Int) {
        findNavController().navigate(R.id.movieDetailFragment)
    }
}