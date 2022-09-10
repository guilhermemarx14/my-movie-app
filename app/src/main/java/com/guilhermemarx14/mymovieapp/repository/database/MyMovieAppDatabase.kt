package com.guilhermemarx14.mymovieapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guilhermemarx14.mymovieapp.model.Genre
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.repository.dao.GenreDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieListItemDAO

@Database(
    entities = [Movie::class, Genre::class, MovieListItem::class],
    version = 1,
    exportSchema = false
)
abstract class MyMovieAppDatabase: RoomDatabase() {
    abstract fun genreDao(): GenreDAO
    abstract fun movieListItemDAO(): MovieListItemDAO
    abstract fun movieDetailsDAO(myMovieAppDatabase: MyMovieAppDatabase): MovieDAO
}