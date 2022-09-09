package com.guilhermemarx14.mymovieapp.repository.datasource

import android.content.Context
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.model.relation.MovieGenreRelation
import com.guilhermemarx14.mymovieapp.repository.database.MyMovieAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatabaseDataSource(
    context: Context
) : MovieDataSource {

    private val myMovieAppDatabase = MyMovieAppDatabase.getDatabase(context)
    private val movieListItemDAO = myMovieAppDatabase.movieListItemDAO()
    private val movieDAO = myMovieAppDatabase.movieDetailsDAO(myMovieAppDatabase)

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

    private suspend fun loadMovieListData() = movieListItemDAO.getAllMovieListItems()

    private fun mapMovieGenreRelationToMovie(movieGenreRelation: MovieGenreRelation): Movie {
        movieGenreRelation.movie.genres = movieGenreRelation.genres
        return movieGenreRelation.movie
    }

    private suspend fun loadMovieDetailsData() = movieDAO.getAllMovieDetails()?.map {
        mapMovieGenreRelationToMovie(it)
    }

}

