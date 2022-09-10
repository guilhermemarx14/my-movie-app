package com.guilhermemarx14.mymovieapp.repository.datasource

import android.util.Log
import com.guilhermemarx14.mymovieapp.model.ImagesResponse
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.model.relation.MovieGenreRelation
import com.guilhermemarx14.mymovieapp.repository.dao.MovieDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieListItemDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDatabaseDataSource @Inject constructor() : MovieDataSource {

    @Inject
    lateinit var movieListItemDAO: MovieListItemDAO

    @Inject
    lateinit var movieDAO: MovieDAO

    override suspend fun getMovieListData(): Result<List<MovieListItem>?> =
        withContext(Dispatchers.IO) {
            Result.success(loadMovieListData())
        }

    override suspend fun saveMovieListData(movies: List<MovieListItem>) {
        movieListItemDAO.insertList(movies)
    }

    override suspend fun clearData() {
        movieListItemDAO.clearMovieListItemData()
    }

    override suspend fun getMovieDetails(id: Int): Result<Movie?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getMovieImages(id: Int): Result<ImagesResponse?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    private suspend fun loadMovieListData() = movieListItemDAO.getAllMovieListItems()

    private fun mapMovieGenreRelationToMovie(movieGenreRelation: MovieGenreRelation): Movie {
        movieGenreRelation.movie.genres = movieGenreRelation.genres
        return movieGenreRelation.movie
    }

    private suspend fun loadMovieDetailsData() = movieDAO.getAllMovieDetails()?.map {
        mapMovieGenreRelationToMovie(it)
    }

}

