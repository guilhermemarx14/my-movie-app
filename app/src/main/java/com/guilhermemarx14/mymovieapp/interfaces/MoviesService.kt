package com.guilhermemarx14.mymovieapp.interfaces

import com.guilhermemarx14.mymovieapp.model.MovieDetails
import com.guilhermemarx14.mymovieapp.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/movie/popular")
    fun getPopularList(
        @Query("api_key") key: String
    ) : Call<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Call<MovieDetails>
}