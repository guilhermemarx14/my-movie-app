package com.guilhermemarx14.mymovieapp.repository.datasource

import android.util.Log
import com.guilhermemarx14.mymovieapp.model.*
import com.guilhermemarx14.mymovieapp.model.relation.MovieGenreRelation
import com.guilhermemarx14.mymovieapp.repository.dao.GenreDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieListItemDAO
import javax.inject.Inject

class LocalDatabaseDataSource @Inject constructor() : MovieDataSource {

    @Inject
    lateinit var movieListItemDAO: MovieListItemDAO

    @Inject
    lateinit var movieDAO: MovieDAO

    @Inject
    lateinit var genreDAO: GenreDAO

    override suspend fun getPopularListData(): Result<List<MovieListItem>?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getTopRatedListData(): Result<List<MovieListItem>?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getNowPlayingListData(): Result<List<MovieListItem>?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getUpcomingListData(): Result<List<MovieListItem>?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
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

    override suspend fun getMovieWatchProviders(id: Int): Result<MovieWatchProvidersResponse?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getMovieCredits(id: Int): Result<CreditsResponse?> {
        Log.d("movieApp", "Not implemented")
        return Result.success(null)
    }

    override suspend fun getGenresList(): Result<List<Genre>?> =
        Result.success(genreDAO.getAllGenres())


    override suspend fun saveGenresList(list: List<Genre>) {
        genreDAO.clear()
        genreDAO.insertList(list)
    }

    private suspend fun loadMovieListData() = movieListItemDAO.getAllMovieListItems()

    private fun mapMovieGenreRelationToMovie(movieGenreRelation: MovieGenreRelation): Movie {
        movieGenreRelation.movie.movieGenres = movieGenreRelation.movieGenres
        return movieGenreRelation.movie
    }

    private suspend fun loadMovieDetailsData() = movieDAO.getAllMovieDetails()?.map {
        mapMovieGenreRelationToMovie(it)
    }

}

