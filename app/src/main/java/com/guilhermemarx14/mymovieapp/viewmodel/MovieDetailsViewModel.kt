package com.guilhermemarx14.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilhermemarx14.mymovieapp.ApiCredentials
import com.guilhermemarx14.mymovieapp.interfaces.MoviesService
import com.guilhermemarx14.mymovieapp.model.DataState
import com.guilhermemarx14.mymovieapp.model.MovieDetails
import com.guilhermemarx14.mymovieapp.model.MovieItemList
import com.guilhermemarx14.mymovieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieDetailsViewModel : ViewModel() {

    val movieDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

    val movieListLiveData: LiveData<List<MovieItemList>?>
        get() = _movieListLiveData
    private val _movieListLiveData =
        MutableLiveData<List<MovieItemList>?>()

    val navigateToDetailsLiveData: LiveData<Unit>
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Unit>()

    val appStateLiveData: LiveData<DataState>
        get() = _appStateLiveData
    private val _appStateLiveData = MutableLiveData<DataState>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieService = retrofit.create(MoviesService::class.java)

    init {
        Log.d("teste","init ViewModel")
        _appStateLiveData.postValue(DataState.LOADING)
        getMovieList()
    }

    fun onMovieSelected(position: Int) {
        Log.d("teste", "onMovieSelected")
        movieListLiveData.value?.get(position)?.id?.let {
            movieService.getMovieDetails(it,ApiCredentials.key)
                .enqueue(object: Callback<MovieDetails>{
                    override fun onResponse(
                        call: Call<MovieDetails>,
                        response: Response<MovieDetails>
                    ) {
                        Log.d("teste", "onMovieSelectedResponse - $response")

                        if(response.isSuccessful){
                            _movieDetailsLiveData.postValue(response.body())
                            _navigateToDetailsLiveData.postValue(Unit)
                        }
                    }

                    override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                        Log.d("teste", "OnFailure")
                        _appStateLiveData.postValue(DataState.ERROR)
                    }

                })
        }

        _navigateToDetailsLiveData.postValue(Unit)
    }

    fun getMovieList(){
        Log.d("teste","getMovieList")
        movieService.getPopularList(ApiCredentials.key)
            .enqueue(object: Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    Log.d("teste", "OnResponse")

                    if(response.isSuccessful){
                        _appStateLiveData.postValue(DataState.SUCCESS)
                        _movieListLiveData.postValue(response.body()?.results)
                    }else{
                        _appStateLiveData.postValue(DataState.ERROR)
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    Log.d("teste", "OnFailure")
                    _appStateLiveData.postValue(DataState.ERROR)
                }

            })
    }
}