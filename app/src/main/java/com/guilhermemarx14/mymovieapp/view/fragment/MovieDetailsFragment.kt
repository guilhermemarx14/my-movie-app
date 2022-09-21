package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.flexbox.FlexboxLayout
import com.guilhermemarx14.mymovieapp.MainActivity
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var carousel: ImageCarousel
    private lateinit var watchProvidersFlexboxLayout: FlexboxLayout
    private lateinit var hostActivity: MainActivity
    private lateinit var linearLayoutDetailsScreen: LinearLayout
    val viewModel by hiltNavGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)
        applyTheme()
        return binding.root
    }

    private fun applyTheme() {
        //linearLayoutDetailsScreen.setBackgroundRecursive(R.color.primaryColor)
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
        carousel = binding.carousel
        hostActivity = activity as MainActivity
        watchProvidersFlexboxLayout = binding.watchProvidersFlexboxLayout
        linearLayoutDetailsScreen = binding.linearLayoutDetailsScreen
        hostActivity.titleTextView.text = ""
    }

}