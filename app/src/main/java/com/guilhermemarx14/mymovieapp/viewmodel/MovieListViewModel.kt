package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemarx14.mymovieapp.model.DataState
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    var repository: MovieRepository
) : ViewModel(){

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

    init {
        viewModelScope.launch {
            repository.getGenresList()
        }
    }
    fun navigateToDetails(){
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
                    Log.e("movieApp", "Failure on getMovieList ${it.message}")
                }
            )

        }
    }
}