package com.guilhermemarx14.mymovieapp.repository.datasource

import android.content.Context
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.service.MoviesService
import com.guilhermemarx14.mymovieapp.util.ApiCredentials
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface MovieDataSource {

    suspend fun getMovieListData(): Result<List<MovieListItem>?>
    suspend fun saveMovieListData(movies: List<MovieListItem>)
    suspend fun clearData()
}