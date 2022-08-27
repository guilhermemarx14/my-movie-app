package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.guilhermemarx14.mymovieapp.viewmodel.MovieViewModel

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.nav_graph){ defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)

        return binding.root
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
    }
}