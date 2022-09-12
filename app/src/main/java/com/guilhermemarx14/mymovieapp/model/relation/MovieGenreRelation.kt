package com.guilhermemarx14.mymovieapp.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.guilhermemarx14.mymovieapp.model.Movie
import com.guilhermemarx14.mymovieapp.model.MovieGenre


data class MovieGenreRelation(
    @Embedded var movie: Movie,
    @Relation(
        entity = MovieGenre::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var movieGenres: List<MovieGenre>
)