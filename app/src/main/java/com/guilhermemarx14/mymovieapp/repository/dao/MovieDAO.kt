package com.guilhermemarx14.mymovieapp.repository.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.relation.MovieGenreRelation
import javax.inject.Inject

@Dao
abstract class MovieDAO(
): BaseDAO<Movie> {
    @Inject
    lateinit var genreDAO : GenreDAO

    @Transaction
    @Query("SELECT * FROM Movie")
    abstract suspend fun getAllMovieDetails(): List<MovieGenreRelation>?

    @Transaction
    @Query("SELECT * FROM Movie where id=:id")
    abstract suspend fun getMovieDetails(id: Int): MovieGenreRelation?

    @Transaction
    open suspend fun insertMovieDetailsList(movies: List<Movie>){
        movies.forEach { insertMovieDetails(it) }
    }

    @Transaction
    open suspend fun insertMovieDetails(movie: Movie){
        movie.genres?.forEach {
            it.movieId = movie.id
        }

        insertEntity(movie)
        movie.genres?.let {
            genreDAO.insertList(it)
        }
    }

}