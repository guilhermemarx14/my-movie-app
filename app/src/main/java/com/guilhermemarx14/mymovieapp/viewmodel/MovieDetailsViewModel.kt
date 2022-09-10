package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.guilhermemarx14.mymovieapp.model.DataState
import com.guilhermemarx14.mymovieapp.model.ImagesResponse
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    var repository: MovieRepository
) : ViewModel(),
    LifecycleEventObserver {

    val movieDetailsLiveData: LiveData<Movie>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie>()

    val movieListLiveData: LiveData<List<MovieListItem>?>
        get() = _movieListLiveData
    private val _movieListLiveData =
        MutableLiveData<List<MovieListItem>?>()

    val navigateToDetailsLiveData: LiveData<Event<Unit>>
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Event<Unit>>()

    val listStateLiveData: LiveData<DataState>
        get() = _listStateLiveData
    private val _listStateLiveData = MutableLiveData<DataState>()

    val detailsStateLiveData: LiveData<DataState>
        get() = _detailsStateLiveData
    private val _detailsStateLiveData = MutableLiveData<DataState>()

    val carouselImagesLiveData: LiveData<ImagesResponse?>
        get() = _carouselImagesLiveData
    private val _carouselImagesLiveData = MutableLiveData<ImagesResponse?>()

    fun onMovieSelected(position: Int) {
        _detailsStateLiveData.postValue(DataState.LOADING)
        movieListLiveData.value?.get(position)?.id?.let { id ->
            viewModelScope.launch {
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

            viewModelScope.launch {
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
        }

        _navigateToDetailsLiveData.postValue(Event(Unit))
    }

    fun getMovieList() {
        _listStateLiveData.postValue(DataState.LOADING)
        viewModelScope.launch {
            val movieListResult = repository.getMovieListData()

            movieListResult.fold(
                onSuccess = {
                    _movieListLiveData.postValue(it)
                    _listStateLiveData.postValue(DataState.SUCCESS)
                },
                onFailure = {
                    _listStateLiveData.postValue(DataState.ERROR)
                    Log.e("movieApp", "${it.message}")
                }
            )

        }
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val ownerName = source.javaClass.name.split('.').last()

        Log.d(
            "movieApp",
            String.format("LifeCycle Event - Owner: %-20s - Event: %s", ownerName, event)
        )
    }
}