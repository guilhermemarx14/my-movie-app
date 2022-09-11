package com.guilhermemarx14.mymovieapp.repository.datasource

import com.guilhermemarx14.mymovieapp.model.*

interface MovieDataSource {
    suspend fun getMovieListData(): Result<List<MovieListItem>?>
    suspend fun saveMovieListData(movies: List<MovieListItem>)
    suspend fun clearData()
    suspend fun getMovieDetails(id: Int): Result<Movie?>
    suspend fun getMovieImages(id: Int): Result<ImagesResponse?>
    suspend fun getMovieWatchProviders(id: Int): Result<MovieWatchProvidersResponse?>
    suspend fun getMovieCredits(id: Int): Result<CreditsResponse?>
}