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
}

@JsonClass(generateAdapter = true)
data class Genre(
    val id:Int?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    val id: Int?,
    val backdrops: List<Image>?,
    val posters: List<Image>?
){
    fun getImages(): List<Image>? {
        val list = mutableListOf<Image>()
        backdrops?.let { list.addAll(it) }
        posters?.let { list.addAll(it) }
        return if (list.isEmpty()) null else list
    }
}

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