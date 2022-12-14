package com.guilhermemarx14.mymovieapp.repository.datasource

import com.guilhermemarx14.mymovieapp.model.*

interface MovieDataSource {

    suspend fun getPopularListData(): Result<List<MovieListItem>?>
    suspend fun getTopRatedListData(): Result<List<MovieListItem>?>
    suspend fun getNowPlayingListData(): Result<List<MovieListItem>?>
    suspend fun getUpcomingListData(): Result<List<MovieListItem>?>
    suspend fun saveMovieListData(movies: List<MovieListItem>)
    suspend fun clearData()
    suspend fun getMovieDetails(id: Int): Result<Movie?>
    suspend fun getMovieImages(id: Int): Result<ImagesResponse?>
    suspend fun getMovieWatchProviders(id: Int): Result<MovieWatchProvidersResponse?>
    suspend fun getMovieCredits(id: Int): Result<CreditsResponse?>
    suspend fun getGenresList(): Result<List<Genre>?>
    suspend fun saveGenresList(list: List<Genre>)
}