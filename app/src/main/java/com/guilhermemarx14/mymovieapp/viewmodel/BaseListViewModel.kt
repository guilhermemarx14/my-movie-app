package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemarx14.mymovieapp.model.DataState
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import kotlinx.coroutines.launch

abstract class BaseListViewModel(val repo: MovieRepository) : ViewModel() {

    open lateinit var type: MovieListType

    val movieListLiveData: LiveData<List<MovieListItem>?>
        get() = _movieListLiveData
    protected val _movieListLiveData =
        MutableLiveData<List<MovieListItem>?>()

    val navigateToDetailsLiveData: LiveData<Event<Unit>>
        get() = _navigateToDetailsLiveData
    protected val _navigateToDetailsLiveData = MutableLiveData<Event<Unit>>()

    val listStateLiveData: LiveData<DataState>
        get() = _listStateLiveData
    protected val _listStateLiveData = MutableLiveData<DataState>()


    fun navigateToDetails(){
        _navigateToDetailsLiveData.postValue(Event(Unit))
    }

    fun getMovieList(){
        _listStateLiveData.postValue(DataState.LOADING)
        viewModelScope.launch {
            val movieListResult = repo.getMovieListData(type)

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