package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.guilhermemarx14.mymovieapp.util.ApiCredentials
import com.guilhermemarx14.mymovieapp.service.MoviesService
import com.guilhermemarx14.mymovieapp.model.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieDetailsViewModel : ViewModel(), LifecycleEventObserver {

    val movieDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

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

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieService = retrofit.create(MoviesService::class.java)

    init {
        _listStateLiveData.postValue(DataState.LOADING)
    }

    fun onMovieSelected(position: Int) {
        Log.d("movieApp", "onMovieSelected")

        _detailsStateLiveData.postValue(DataState.LOADING)
        movieListLiveData.value?.get(position)?.id?.let {id ->

            viewModelScope.launch {
                Log.d("movieApp", "getMovieDetails")
                val response = movieService.getMovieDetails(id, ApiCredentials.key)

                if(response.isSuccessful){
                    Log.d("movieApp", "getMovieDetails - Success")
                    _detailsStateLiveData.postValue(DataState.SUCCESS)
                    _movieDetailsLiveData.postValue(response.body())
                }else{
                    Log.e("movieApp", "${response.errorBody()}")
                    _detailsStateLiveData.postValue(DataState.ERROR)
                    //_appStateLiveData.postValue(DataState.ERROR)
                }
            }

            viewModelScope.launch {
                Log.d("movieApp", "getMovieImages")
                val response = movieService.getMovieImages(id, ApiCredentials.key)

                if(response.isSuccessful){
                    Log.d("movieApp", "getMovieImages - Success")
                    _carouselImagesLiveData.postValue(response.body())
                }else{
                    Log.e("movieApp", "${response.errorBody()}")
                    //_appStateLiveData.postValue(DataState.ERROR)
                }
            }
        }

        _navigateToDetailsLiveData.postValue(Event(Unit))
    }

    fun getMovieList(){
        viewModelScope.launch {
            Log.d("movieApp", "getPopularList")
            val response = movieService.getPopularList(ApiCredentials.key)

            if(response.isSuccessful){
                Log.d("movieApp", "getPopularList - Success")
                _listStateLiveData.postValue(DataState.SUCCESS)
                _movieListLiveData.postValue(response.body()?.results)
            }else{
                Log.e("movieApp", "${response.errorBody()}")
                _listStateLiveData.postValue(DataState.ERROR)
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val ownerName = source.javaClass.name.split('.').last()

        Log.d("movieApp", String.format("LifeCycle Event - Owner: %-20s - Event: %s", ownerName, event))
    }
}