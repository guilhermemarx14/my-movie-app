package com.guilhermemarx14.mymovieapp.util

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.guilhermemarx14.mymovieapp.model.Genre
import java.math.BigDecimal
import java.math.RoundingMode

class Util {
    companion object {

        var movieGenres: List<Genre>? = null
        fun configureCardAppearance(card: CardView, radius: Float, elevation: Float) {
            card.radius = radius
            card.cardElevation = elevation
        }

        fun configureCardLayout(card: CardView, margins: List<Int>?) {
            val cardViewParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            card.layoutParams = cardViewParams
            margins?.let {
                (card.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                    margins[0],
                    margins[1],
                    margins[2],
                    margins[3]
                )
            }
        }

        fun formatDouble(number: Double, numberOfCases: Int): BigDecimal {
            return BigDecimal(number).setScale(numberOfCases, RoundingMode.HALF_EVEN)
        }

        fun getGenres(ids: List<Int>?): List<Genre>? {
            return ids?.let {
                movieGenres?.filter { it.id in ids }
            }
        }

    }
}