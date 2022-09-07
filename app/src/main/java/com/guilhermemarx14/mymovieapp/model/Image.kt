package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    //val aspect_ratio: Double?,
    val file_path: String?,
    //val height: Int?,
    //val iso_639_1: String?,
    //val vote_average: Int?,
    //val vote_count: Int?,
    //val width: Int?
){
    fun getImagePath() = file_path?.let { "https://image.tmdb.org/t/p/w500${file_path}" } ?: ""
}