package com.guilhermemarx14.mymovieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    val page: Int?,
    val total_results: Int?,
    val total_pages: Int?,
    val results: List<MovieListItem>?
)

@Entity
@JsonClass(generateAdapter = true)
data class MovieListItem(
    @PrimaryKey val id: Int?,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?
){
    fun getTitleWithYear():String?{
        release_date?.let {
            return "$title (${release_date.split('-')[0]})"
        }
        return title
    }

    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${poster_path}" } ?: ""

}