package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieWatchProvidersResponse(
    val id: Int?,
    val results: ResultRegion?
)

@JsonClass(generateAdapter = true)
data class ResultRegion(
    val US: MovieWatchProvider?
)

@JsonClass(generateAdapter = true)
data class MovieWatchProvider(
    val link: String?,
    val flatrate: List<WatchProvider>?,
    val rent: List<WatchProvider>?,
    val buy: List<WatchProvider>?
)

@JsonClass(generateAdapter = true)
data class WatchProvider(
    val display_priority: Int?,
    val logo_path: String?,
    val provider_id: Int?,
    val provider_name: String?
){
    fun getLogoPath() = logo_path?.let { "https://image.tmdb.org/t/p/original${logo_path}" } ?: ""
}