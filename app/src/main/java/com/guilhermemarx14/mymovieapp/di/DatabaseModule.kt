package com.guilhermemarx14.mymovieapp.di

import android.content.Context
import androidx.room.Room
import com.guilhermemarx14.mymovieapp.repository.dao.GenreDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieGenreDAO
import com.guilhermemarx14.mymovieapp.repository.dao.MovieListItemDAO
import com.guilhermemarx14.mymovieapp.repository.database.MyMovieAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMovieListItemDAO(db: MyMovieAppDatabase): MovieListItemDAO =
        db.movieListItemDAO()

    @Provides
    fun provideMovieGenreDAO(db: MyMovieAppDatabase): MovieGenreDAO =
        db.movieGenreDao()

    @Provides
    fun provideMovieDAO(db: MyMovieAppDatabase): MovieDAO =
        db.movieDetailsDAO(db)

    @Provides
    fun provideGenreDAO(db: MyMovieAppDatabase): GenreDAO =
        db.genreDao()

    @Provides
    @Singleton
    fun provideMyMovieAppDatabase(
        @ApplicationContext context: Context
    ): MyMovieAppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MyMovieAppDatabase::class.java,
            "MyMovieAppDatabase"
        ).build()


}