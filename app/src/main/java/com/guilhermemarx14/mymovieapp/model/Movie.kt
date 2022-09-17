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
    var movieGenres: List<MovieGenre>? = null
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
        movieGenres: List<MovieGenre>?,
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
        this.movieGenres = movieGenres
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


    fun getReleaseDateWithRuntime():String?{
        var returnString: String
        release_date?.let {
            returnString = Util.formatDateFromString(it,"MMMM dd, yyyy")
            runtime?.let {
                returnString += " | $runtime min"
            }
            return returnString
        }
        runtime?.let {
            returnString = "$runtime min"
            return returnString
        }
        return null
    }

    fun getBudgetText():String?{
        return budget?.let {
            "Budget: \$ $it,00"
        }
    }

    fun getRevenueText():String?{
        return revenue?.toString()
    }

    fun getImagePath() = poster_path?.let { "https://image.tmdb.org/t/p/w500${it}" } ?: ""

    fun getRatings() = vote_average?.let {
        "Ratings: ${Util.roundDouble(it, 1)}"
    }
}
