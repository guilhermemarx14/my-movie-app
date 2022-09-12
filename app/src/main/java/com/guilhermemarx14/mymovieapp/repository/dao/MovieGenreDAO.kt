package com.guilhermemarx14.mymovieapp.repository.dao

import androidx.room.Dao
import androidx.room.Query
import com.guilhermemarx14.mymovieapp.model.MovieGenre

@Dao
interface MovieGenreDAO: BaseDAO<MovieGenre> {

    @Query("SELECT * FROM MovieGenre")
    suspend fun getAllGenres(): List<MovieGenre>?

    @Query("SELECT * FROM MovieGenre WHERE id = :id")
    suspend fun getGenre(id: Int): MovieGenre?

}