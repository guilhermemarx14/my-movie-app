package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListItem(
    val id: Int?,
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