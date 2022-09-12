package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemarx14.mymovieapp.model.*
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    var repository: MovieRepository
) : ViewModel() {

    val movieDetailsLiveData: LiveData<Movie?>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie?>()

    val carouselImagesLiveData: LiveData<ImagesResponse?>
        get() = _carouselImagesLiveData
    private val _carouselImagesLiveData = MutableLiveData<ImagesResponse?>()

    val watchProvidersLiveData: LiveData<List<WatchProvider>?>
        get() = _watchProvidersLiveData
    private val _watchProvidersLiveData = MutableLiveData<List<WatchProvider>?>()

    val creditsLiveData: LiveData<CreditsResponse?>
        get() = _creditsLiveData
    private val _creditsLiveData = MutableLiveData<CreditsResponse?>()

    val detailsStateLiveData: LiveData<DataState>
        get() = _detailsStateLiveData
    private val _detailsStateLiveData = MutableLiveData<DataState>()

    fun getMovie(id: Int) {
        _detailsStateLiveData.postValue(DataState.LOADING)

        viewModelScope.launch { getMovieDetails(id) }

        viewModelScope.launch { getMovieImages(id) }

        viewModelScope.launch { getMovieWatchProviders(id) }

        viewModelScope.launch { getMovieCredits(id) }
    }

    private suspend fun getMovieDetails(id: Int) {
        val response = repository.getMovieDetails(id)

        response.fold(
            onSuccess = {
                _detailsStateLiveData.postValue(DataState.SUCCESS)
                _movieDetailsLiveData.postValue(it)
            },
            onFailure = {
                _detailsStateLiveData.postValue(DataState.ERROR)
                Log.e("movieApp", "${it.message}")
            }
        )
    }

    private suspend fun getMovieImages(id: Int) {
        val response = repository.getMovieImages(id)

        response.fold(
            onSuccess = {
                _carouselImagesLiveData.postValue(it)
            },
            onFailure = {
                Log.e("movieApp", "${it.message}")
            }
        )
    }

    private suspend fun getMovieWatchProviders(id: Int) {
        val response = repository.getMovieWatchProviders(id)

        response.fold(
            onSuccess = {
                _watchProvidersLiveData.postValue(it?.results?.BR?.flatrate)
            },
            onFailure = {
                Log.e("movieApp", "${it.message}")
            }
        )
    }

    private suspend fun getMovieCredits(id: Int) {
        val response = repository.getMovieCredits(id)

        response.fold(
            onSuccess = {
                _creditsLiveData.postValue(it)
            },
            onFailure = {
                Log.e("movieApp", "${it.message}")
            }
        )
    }
}