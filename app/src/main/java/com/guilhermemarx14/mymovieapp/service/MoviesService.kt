package com.guilhermemarx14.mymovieapp.service

import com.guilhermemarx14.mymovieapp.model.ImagesResponse
import com.guilhermemarx14.mymovieapp.model.MovieDetails
import com.guilhermemarx14.mymovieapp.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/movie/popular")
    suspend fun getPopularList(
        @Query("api_key") key: String
    ) : Response<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Response<MovieDetails>

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Response<ImagesResponse>
}