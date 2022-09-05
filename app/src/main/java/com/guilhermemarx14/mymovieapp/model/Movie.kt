package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    val page: Int?,
    val total_results: Int?,
    val total_pages: Int?,
    val results: List<MovieItemList>?
)

@JsonClass(generateAdapter = true)
data class MovieItemList(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val poster_path: String?
){
    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${poster_path}" } ?: ""

}

@JsonClass(generateAdapter = true)
data class MovieDetails(
    val id: Int?,
    val adult: Boolean?,
    val poster_path: String?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val imdb_id: String?,
    val original_language: String?,
    val tagline: String?,
    val title: String?,
    val overview: String?

){
    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${poster_path}" } ?: ""

}

@JsonClass(generateAdapter = true)
data class Genre(
    val id:Int?,
    val name: String?
)