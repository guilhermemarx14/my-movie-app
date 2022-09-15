package com.guilhermemarx14.mymovieapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.guilhermemarx14.mymovieapp.MainActivity
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.databinding.FragmentMainScreenBinding
import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.viewmodel.MainScreenViewModel

class MainScreenFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var hostActivity: MainActivity
    private val mainScreenViewModel by hiltNavGraphViewModels<MainScreenViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupDataBinding(inflater,container)
        setupRedirectionButtons()


        return binding.root
    }

    private fun setupRedirectionButtons(){
        hostActivity.navDrawer.setNavigationItemSelectedListener(this)

        mainScreenViewModel.navigateToListLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { type ->
                val action = MainScreenFragmentDirections.actionMainScreenFragmentToMovieListFragment(type)
                findNavController().navigate(action)
            }
        }
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)

        hostActivity = activity as MainActivity
        binding.lifecycleOwner = this
        hostActivity.titleTextView.text = resources.getString(R.string.main_screen_title)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nowPlayingMenuItem -> mainScreenViewModel.navigateToList(MovieListType.NOW_PLAYING)
            R.id.popularMenuItem -> mainScreenViewModel.navigateToList(MovieListType.POPULAR)
            R.id.topRatedMenuItem -> mainScreenViewModel.navigateToList(MovieListType.TOP_RATED)
            R.id.upcomingMenuItem -> mainScreenViewModel.navigateToList(MovieListType.UPCOMING)
        }
        hostActivity.drawer.close()
        return true
    }

}