package com.guilhermemarx14.mymovieapp.repository


import android.util.Log
import com.guilhermemarx14.mymovieapp.model.*
import com.guilhermemarx14.mymovieapp.repository.datasource.LocalDatabaseDataSource
import com.guilhermemarx14.mymovieapp.repository.datasource.TmdbDataSource
import com.guilhermemarx14.mymovieapp.util.Util
import javax.inject.Inject

class MovieRepository @Inject constructor(
    var tmdbDataSource: TmdbDataSource,
    var localDatabaseDataSource: LocalDatabaseDataSource
) {


    suspend fun getMovieListData(type: MovieListType): Result<List<MovieListItem>?> =
        try {
            val list = when (type) {
                MovieListType.POPULAR -> {
                    Log.d("movieApp","getPopularListData")
                    tmdbDataSource.getPopularListData()
                }
                MovieListType.NOW_PLAYING -> {
                    Log.d("movieApp","getNowPlayingListData")
                    tmdbDataSource.getNowPlayingListData()
                }
                MovieListType.TOP_RATED -> {
                    Log.d("movieApp","getTopRatedListData")
                    tmdbDataSource.getTopRatedListData()
                }
                MovieListType.UPCOMING -> {
                    Log.d("movieApp","getUpcomingListData")
                    tmdbDataSource.getUpcomingListData()
                }
            }
            //if (list.isSuccess)
                //persistData(list.getOrNull())
            list
        } catch (e: Exception) {
            Result.failure(e)
        }


    private suspend fun persistData(movieList: List<MovieListItem>?) {
        movieList?.let {
            localDatabaseDataSource.clearData()
            localDatabaseDataSource.saveMovieListData(it)
        }
    }

    suspend fun getMovieDetails(id: Int): Result<Movie?> =
        tmdbDataSource.getMovieDetails(id)

    suspend fun getMovieImages(id: Int): Result<ImagesResponse?> =
        tmdbDataSource.getMovieImages(id)

    suspend fun getMovieWatchProviders(id: Int): Result<MovieWatchProvidersResponse?> =
        tmdbDataSource.getMovieWatchProviders(id)

    suspend fun getMovieCredits(id: Int): Result<CreditsResponse?> =
        tmdbDataSource.getMovieCredits(id)

    suspend fun getGenresList() {
        if (Util.movieGenres.isNullOrEmpty())
            Util.movieGenres = localDatabaseDataSource.getGenresList().getOrNull()

        if (Util.movieGenres.isNullOrEmpty()) {
            val retrofitList = tmdbDataSource.getGenresList()

            if (retrofitList.isSuccess) {
                localDatabaseDataSource.saveGenresList(retrofitList.getOrNull()!!)
                Util.movieGenres = retrofitList.getOrNull()
            }
        }
    }
}


//fun onMovieSelected(position: Int) {
//    Log.d("movieApp", "onMovieSelected")
//
//    _detailsStateLiveData.postValue(DataState.LOADING)
//    movieListLiveData.value?.get(position)?.id?.let { id ->
//
//        viewModelScope.launch {
//            Log.d("movieApp", "getMovieDetails")
//            val response = movieService.getMovieDetails(id, ApiCredentials.key)
//
//            if (response.isSuccessful) {
//                Log.d("movieApp", "getMovieDetails - Success")
//                _detailsStateLiveData.postValue(DataState.SUCCESS)
//                _movieDetailsLiveData.postValue(response.body())
//            } else {
//                Log.e("movieApp", "${response.errorBody()}")
//                _detailsStateLiveData.postValue(DataState.ERROR)
//                //_appStateLiveData.postValue(DataState.ERROR)
//            }
//        }
//
//        viewModelScope.launch {
//            Log.d("movieApp", "getMovieImages")
//            val response = movieService.getMovieImages(id, ApiCredentials.key)
//
//            if (response.isSuccessful) {
//                Log.d("movieApp", "getMovieImages - Success")
//                _carouselImagesLiveData.postValue(response.body())
//            } else {
//                Log.e("movieApp", "${response.errorBody()}")
//                //_appStateLiveData.postValue(DataState.ERROR)
//            }
//        }
//    }
//
//    _navigateToDetailsLiveData.postValue(Event(Unit))
//}

//
//private suspend fun errorHandling(){
//    val movieList = loadMovieListData()
//    if(movieList.isNullOrEmpty()){
//        Log.e("movieApp", "Handling error failed. MovieList on database is null or empty.")
//        _listStateLiveData.postValue(DataState.ERROR)
//    } else {
//        Log.d("movieApp", "Error Handled. MovieList loaded from database.")
//        _movieListLiveData.postValue(movieList)
//        _listStateLiveData.postValue(DataState.SUCCESS)
//    }
//
//}
//

//


//

//


