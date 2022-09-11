package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.flexbox.FlexboxLayout
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.guilhermemarx14.mymovieapp.model.WatchProvider
import com.guilhermemarx14.mymovieapp.util.Util
import com.guilhermemarx14.mymovieapp.view.binder.bindSrcUrl
import com.guilhermemarx14.mymovieapp.viewmodel.MovieDetailsViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var carousel: ImageCarousel
    private lateinit var watchProvidersFlexboxLayout: FlexboxLayout
    val viewModel by hiltNavGraphViewModels<MovieDetailsViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)
        setupWatchProviders()
        return binding.root
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        binding.lifecycleOwner = this
        binding.movieDetailViewModel = viewModel
        carousel = binding.carousel
        watchProvidersFlexboxLayout = binding.watchProvidersFlexboxLayout
    }

    private fun setupWatchProviders(){
        viewModel.watchProvidersLiveData.observe(viewLifecycleOwner) {
            it?.let { providersResponse ->
                watchProvidersFlexboxLayout.removeAllViews()
                providersResponse.results?.BR?.flatrate?.forEach { provider ->
                    watchProvidersFlexboxLayout.addView(getProviderCard(provider))
                }
            }
        }
    }

    private fun getProviderCard(provider: WatchProvider): CardView{
        val providerCard = CardView(requireContext())

        Util.configureCardLayout(providerCard, listOf(0, 0, 0, 20))
        Util.configureCardAppearance(providerCard, 60F,20F)

        providerCard.addView(configureProviderImageView(provider,2))
        return providerCard
    }



    private fun configureProviderImageView(provider: WatchProvider, scale: Int): ImageView{
        val imageView = ImageView(requireContext())

        imageView.layoutParams = LinearLayout.LayoutParams(100,100)
        imageView.layoutParams.width *= scale
        imageView.layoutParams.height *= scale

        imageView.bindSrcUrl(provider.getLogoPath())
        return imageView
    }

}