package com.guilhermemarx14.mymovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemarx14.mymovieapp.database.MyMovieAppDatabase
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.relation.MovieGenreRelation

@Dao
abstract class MovieDAO(
    myMovieAppDatabase: MyMovieAppDatabase
): BaseDAO<Movie>{
    private val genreDAO = myMovieAppDatabase.genreDao()

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