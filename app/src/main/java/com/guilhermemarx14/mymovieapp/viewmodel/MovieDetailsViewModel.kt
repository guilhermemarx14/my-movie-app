package com.guilhermemarx14.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.view.fragment.placeholder.PlaceholderContent
import com.guilhermemarx14.mymovieapp.view.fragment.placeholder.PlaceholderContent.PlaceholderItem

enum class DataState {
    ERROR, LOADING, SUCCESS
}

class MovieDetailsViewModel : ViewModel() {

    private val loremIpsum =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed semper lacinia odio, eu ultricies tortor dignissim ac. Phasellus porttitor suscipit malesuada. Curabitur vel quam ultrices, iaculis odio sed, laoreet ligula. Nullam tincidunt eget sem eu mattis. Integer et gravida ante. Nunc tincidunt commodo quam, sit amet tristique lacus lacinia vel. Sed mattis porta euismod."

    val movieDetailsLiveData: LiveData<Movie>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie>()

    val movieListLiveData: LiveData<MutableList<PlaceholderItem>>
        get() = _movieListLiveData
    private val _movieListLiveData =
        MutableLiveData<MutableList<PlaceholderItem>>()

    val navigateToDetailsLiveData: LiveData<Unit>
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Unit>()

    val dataStateLiveData: LiveData<DataState>
        get() = _dataStateLiveData
    private val _dataStateLiveData = MutableLiveData<DataState>()

    init {
        _movieListLiveData.postValue(PlaceholderContent.ITEMS)
    }

    fun onMovieSelected(position: Int) {
        val movie = Movie("Meu Título - $position", loremIpsum)
        _movieDetailsLiveData.postValue(movie)
        _navigateToDetailsLiveData.postValue(Unit)
    }


}