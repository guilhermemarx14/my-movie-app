package com.guilhermemarx14.mymovieapp.viewmodel.listViewModels

import com.guilhermemarx14.mymovieapp.model.MovieListType
import com.guilhermemarx14.mymovieapp.repository.MovieRepository
import com.guilhermemarx14.mymovieapp.viewmodel.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieListViewModel @Inject constructor(
    var repository: MovieRepository
) : BaseListViewModel(repository){
    override var type = MovieListType.UPCOMING
}