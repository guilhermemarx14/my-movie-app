package com.guilhermemarx14.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    var repository: MovieRepository
) : ViewModel() {

    val navigateToListLiveData: LiveData<Event<MovieListType>>
        get() = _navigateToListLiveData
    private val _navigateToListLiveData = MutableLiveData<Event<MovieListType>>()

    init {
        viewModelScope.launch {
            repository.getGenresList()
        }
    }

    fun navigateToList(type: MovieListType){
        _navigateToListLiveData.postValue(Event(type))
    }
}