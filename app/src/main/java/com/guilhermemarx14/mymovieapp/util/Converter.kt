package com.guilhermemarx14.mymovieapp.util

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromList(value: List<Int>?): String? = value?.toString()

    @TypeConverter
    fun toList(value: String?): List<Int>? =
        value?.let{
            val stringList = value.removePrefix("[").removeSuffix("]").replace(" ", "").split(',').toList()
            val intList = mutableListOf<Int>()
            stringList.forEach { intList.add(Integer.parseInt(it)) }
            intList
        }
}