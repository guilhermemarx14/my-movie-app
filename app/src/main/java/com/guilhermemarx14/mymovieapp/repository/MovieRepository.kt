package com.guilhermemarx14.mymovieapp.repository


import android.content.Context
import com.guilhermemarx14.mymovieapp.model.MovieListItem
import com.guilhermemarx14.mymovieapp.repository.datasource.LocalDatabaseDataSource
import com.guilhermemarx14.mymovieapp.repository.datasource.TmdbDataSource

class MovieRepository(context: Context) {
    private val tmdbDataSource = TmdbDataSource()
    private val localDatabaseDataSource = LocalDatabaseDataSource(context)

    suspend fun getMovieListData(): Result<List<MovieListItem>?> {
        return try {
            val tmdbList = tmdbDataSource.getMovieListData()

            if (tmdbList.isSuccess){
                persistData(tmdbList.getOrNull())
                tmdbList
            } else localDatabaseDataSource.getMovieListData()
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    private suspend fun persistData(movieList: List<MovieListItem>?){
        movieList?.let {
            localDatabaseDataSource.clearData()
            localDatabaseDataSource.saveMovieListData(it)
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


