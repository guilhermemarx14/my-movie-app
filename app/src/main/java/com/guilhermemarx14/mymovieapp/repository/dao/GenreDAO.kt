package com.guilhermemarx14.mymovieapp.repository.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemarx14.mymovieapp.model.Genre

@Dao
interface GenreDAO : BaseDAO<Genre>{

    @Query("SELECT * FROM Genre")
    suspend fun getAllGenres(): List<Genre>?

    @Query("SELECT * FROM Genre WHERE id = :id")
    suspend fun getGenre(id: Int): Genre?

    @Transaction
    @Query("DELETE FROM Genre")
    suspend fun clear()

}