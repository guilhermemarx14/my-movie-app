package com.guilhermemarx14.mymovieapp.repository.datasource

import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.service.MoviesService
import com.guilhermemarx14.mymovieapp.util.ApiCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TmdbDataSource : MovieDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieService = retrofit.create(MoviesService::class.java)

    override suspend fun getMovieListData(): Result<List<MovieListItem>?> =
        withContext(Dispatchers.IO) {
            val response = movieService.getPopularList(ApiCredentials.key)

            when {
                response.isSuccessful -> Result.success(response.body()?.results)
                else -> Result.failure(Throwable(response.message()))

            }
        }

    override suspend fun saveMovieListData(movies: List<MovieListItem>) {
        print("Não suportado")
    }

    override suspend fun clearData() {
        print("Não suportado")
    }
}