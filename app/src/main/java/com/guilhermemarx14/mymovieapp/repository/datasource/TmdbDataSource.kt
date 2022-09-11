package com.guilhermemarx14.mymovieapp.repository.datasource

import com.guilhermemarx14.mymovieapp.model.*
import com.guilhermemarx14.mymovieapp.service.MoviesService
import com.guilhermemarx14.mymovieapp.util.ApiCredentials
import com.guilhermemarx14.mymovieapp.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TmdbDataSource @Inject constructor() : MovieDataSource {

    @Inject
    lateinit var movieService : MoviesService

    override suspend fun getMovieListData(): Result<List<MovieListItem>?> =
        withContext(Dispatchers.IO) {
            if (Util.genres == null)
                Util.genres = getAllGenres().getOrNull()?.genres

            val response = movieService.getPopularList(ApiCredentials.key)

            when {
                response.isSuccessful ->{
                    Result.success(response.body()?.results)
                }
                else -> Result.failure(Throwable(response.message()))

            }
        }

    override suspend fun saveMovieListData(movies: List<MovieListItem>) {
        print("Não suportado")
    }

    override suspend fun clearData() {
        print("Não suportado")
    }

    override suspend fun getMovieDetails(id: Int): Result<Movie?> =
        withContext(Dispatchers.IO) {
            if (Util.genres == null)
                Util.genres = getAllGenres().getOrNull()?.genres
            val response = movieService.getMovieDetails(id, ApiCredentials.key)
            when{
                response.isSuccessful -> Result.success(response.body())
                else -> Result.failure(Throwable(response.message()))
            }
        }

    override suspend fun getMovieImages(id: Int): Result<ImagesResponse?> =
        withContext(Dispatchers.IO) {
            val response = movieService.getMovieImages(id, ApiCredentials.key)
            when{
                response.isSuccessful -> Result.success(response.body())
                else -> Result.failure(Throwable(response.message()))
            }
        }

    override suspend fun getMovieWatchProviders(id: Int): Result<MovieWatchProvidersResponse?> =
        withContext(Dispatchers.IO) {
            val response = movieService.getMovieWatchProviders(id, ApiCredentials.key, "BR")
            when{
                response.isSuccessful -> Result.success(response.body())
                else -> Result.failure(Throwable(response.message()))
            }
        }

    override suspend fun getMovieCredits(id: Int): Result<CreditsResponse?> =
        withContext(Dispatchers.IO) {
            val response = movieService.getMovieCredits(id, ApiCredentials.key)
            when{
                response.isSuccessful -> Result.success(response.body())
                else -> Result.failure(Throwable(response.message()))
            }
        }

    override suspend fun getAllGenres(): Result<GenresResponse?> =
        withContext(Dispatchers.IO) {
            val response = movieService.getAllGenres(ApiCredentials.key)
            when{
                response.isSuccessful -> Result.success(response.body())
                else -> Result.failure(Throwable(response.message()))
            }
        }



}