package com.guilhermemarx14.mymovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.guilhermemarx14.mymovieapp.model.Genre

@Dao
interface GenreDAO: BaseDAO<Genre> {

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<Genre>?

    @Query("SELECT * FROM genre WHERE id = :id")
    suspend fun getGenre(id: Int): Genre?

}