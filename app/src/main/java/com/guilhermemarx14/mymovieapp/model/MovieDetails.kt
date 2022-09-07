package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import java.math.RoundingMode

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
    val vote_average: Double?,
    val tagline: String?,
    val title: String?,
    val overview: String?,
    val release_date: String?

){
    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${poster_path}" } ?: ""

    fun getTitleWithYear():String?{
        release_date?.let {
            return "$title (${release_date.split('-')[0]})"
        }
        return title
    }

    fun getRatings() = vote_average?.let{
        "Ratings: ${BigDecimal(vote_average).setScale(1, RoundingMode.HALF_EVEN)}"
    }
}