package com.guilhermemarx14.mymovieapp.util

import com.guilhermemarx14.mymovieapp.model.Genre
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Util {
    companion object {

        var movieGenres: List<Genre>? = null
        var region = "US"
        var language= "en-US"

        fun roundDouble(number: Double, numberOfCases: Int): BigDecimal {
            return BigDecimal(number).setScale(numberOfCases, RoundingMode.HALF_EVEN)
        }

        fun formatDateFromString(stringDate: String, pattern: String): String {
            val date = LocalDate.parse(stringDate)
            val formatter = DateTimeFormatter.ofPattern(pattern)
            return date.format(formatter)

        }

        fun getGenres(ids: List<Int>?): List<Genre>? {
            return ids?.let {
                movieGenres?.filter { it.id in ids }
            }
        }



    }
}