package com.guilhermemarx14.mymovieapp.repository.datasource

import com.guilhermemarx14.mymovieapp.model.ImagesResponse
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieListItem

interface MovieDataSource {
    suspend fun getMovieListData(): Result<List<MovieListItem>?>
    suspend fun saveMovieListData(movies: List<MovieListItem>)
    suspend fun clearData()
    suspend fun getMovieDetails(id: Int): Result<Movie?>
    suspend fun getMovieImages(id: Int): Result<ImagesResponse?>
}