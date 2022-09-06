package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.guilhermemarx14.mymovieapp.lifecycle_observers.FragmentObserver
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var carousel: ImageCarousel
    private val carouselImagesList = mutableListOf<CarouselItem>()
    private lateinit var postersAndBackdropsTextView: TextView
    private val viewModel by navGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph){ defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)
        setupCarousel()
        lifecycle.addObserver(FragmentObserver())
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

    private fun setupCarousel(){
        carousel.registerLifecycle(this)
        viewModel.carouselImagesLiveData.observe(viewLifecycleOwner) { list ->
            Log.d("movieApp","carouselImagesLiveData.observe")
            carouselImagesList.clear()
            list?.forEach {
                carouselImagesList.add(CarouselItem(imageUrl = it.getImagePath()))
            }
            carousel.visibility = if (carouselImagesList.isEmpty()) View.GONE else View.VISIBLE
            postersAndBackdropsTextView.visibility = if (carouselImagesList.isEmpty()) View.GONE else View.VISIBLE
            carousel.setData(carouselImagesList)

        }
    }

}