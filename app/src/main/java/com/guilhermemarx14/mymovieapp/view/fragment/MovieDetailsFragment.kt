package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var carousel: ImageCarousel
    private lateinit var postersAndBackdropsTextView: TextView
    private val viewModel by navGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph){ defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
        carousel = binding.carousel
        postersAndBackdropsTextView = binding.postersAndBackdropsTextView
    }

}