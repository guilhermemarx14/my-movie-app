package com.guilhermemarx14.mymovieapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.guilhermemarx14.mymovieapp.util.Util
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
class Movie() {

    @PrimaryKey
    var id: Int? = null
    var poster_path: String? = null

    @Ignore
    var genres: List<Genre>? = null
    var vote_average: Double? = null
    var tagline: String? = null
    var title: String? = null
    var overview: String? = null
    var release_date: String? = null
    var budget: Int? = null
    var imdb_id: String? = null
    var popularity: Double? = null
    var revenue: Int? = null
    var runtime: Int? = null
    var status: String? = null

    @Ignore
    constructor(
        id: Int?,
        poster_path: String?,
        genres: List<Genre>?,
        vote_average: Double?,
        tagline: String?,
        title: String?,
        overview: String?,
        release_date: String?,
        budget: Int?,
        imdb_id: String?,
        popularity: Double?,
        revenue: Int?,
        runtime: Int?,
        status: String?
    ) : this() {
        this.id = id
        this.poster_path = poster_path
        this.genres = genres
        this.vote_average = vote_average
        this.tagline = tagline
        this.title = title
        this.overview = overview
        this.release_date = release_date
        this.budget = budget
        this.imdb_id = imdb_id
        this.popularity = popularity
        this.revenue = revenue
        this.runtime = runtime
        this.status = status
    }


    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${it}" } ?: ""

    fun getTitleWithYear() = release_date?.let { "$title (${it.split('-')[0]})" } ?: title


    fun getRatings() = vote_average?.let {
        "Ratings: ${Util.formatDouble(it, 1)}"
    }
}
