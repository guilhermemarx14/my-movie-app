package com.guilhermemarx14.mymovieapp.service

import com.guilhermemarx14.mymovieapp.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/movie/top_rated")
    suspend fun getPopularList(
        @Query("api_key") key: String
    ) : Response<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Response<Movie>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Response<CreditsResponse>

    @GET("/3/movie/{movie_id}/watch/providers")
    suspend fun getMovieWatchProviders(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String,
        @Query("region") region: String
    ) : Response<MovieWatchProvidersResponse>

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ) : Response<ImagesResponse>

    @GET("/3/genre/movie/list")
    suspend fun getAllGenres(
        @Query("api_key") key: String
    ) : Response<GenresResponse>
}