package com.guilhermemarx14.mymovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemarx14.mymovieapp.model.MovieListItem

@Dao
interface MovieListItemDAO: BaseDAO<MovieListItem> {

    @Query("SELECT * FROM MovieListItem")
    suspend fun getAllMovieListItems(): List<MovieListItem>?

    @Query("SELECT * FROM MovieListItem WHERE id = :id")
    suspend fun getMovieListItem(id: Int): MovieListItem?

    @Transaction
    @Query("DELETE FROM MovieListItem")
    suspend fun clearMovieListItemData()

}