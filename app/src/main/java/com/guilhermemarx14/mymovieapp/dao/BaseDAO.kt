package com.guilhermemarx14.mymovieapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDAO<T> {

    @Insert
    suspend fun insertEntity(entity: T)

    @Insert
    suspend fun insertList(list: List<T>)

    @Update
    suspend fun updateEntity(entity: T)

    @Update
    suspend fun updateList(list: List<T>)

    @Delete
    suspend fun deleteEntity(entity: T)

    @Delete
    suspend fun deleteList(list: List<T>)

}