package com.guilhermemarx14.mymovieapp.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.guilhermemarx14.mymovieapp.model.Genre
import com.guilhermemarx14.mymovieapp.model.Movie


data class MovieGenreRelation(
    @Embedded var movie: Movie,
    @Relation(
        entity = Genre::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var genres: List<Genre>
)