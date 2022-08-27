package com.guilhermemarx14.mymovieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.guilhermemarx14.mymovieapp.model.Movie

class MovieViewModel: ViewModel() {

    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed semper lacinia odio, eu ultricies tortor dignissim ac. Phasellus porttitor suscipit malesuada. Curabitur vel quam ultrices, iaculis odio sed, laoreet ligula. Nullam tincidunt eget sem eu mattis. Integer et gravida ante. Nunc tincidunt commodo quam, sit amet tristique lacus lacinia vel. Sed mattis porta euismod."

    fun loadMovieDetail(): Movie{
        return Movie("Meu Título", loremIpsum)
    }
}