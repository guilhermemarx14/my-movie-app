package com.guilhermemarx14.mymovieapp.model

import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    val id: Int?,
    val backdrops: List<Image>?,
    val posters: List<Image>?
){
    private fun getImages(): List<Image>? {
        val list = mutableListOf<Image>()
        backdrops?.let { list.addAll(it) }
        posters?.let { list.addAll(it) }
        return if (list.isEmpty()) null else list
    }

    fun getCarouselItems(): List<CarouselItem>? = getImages()?.map { CarouselItem(imageUrl = it.getImagePath()) }
}