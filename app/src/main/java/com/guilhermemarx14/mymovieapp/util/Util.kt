package com.guilhermemarx14.mymovieapp.util

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.children
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

        fun configureCardLayout(
            card: CardView,
            margins: List<Int>?,
            widthParam: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            heightParam:Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            minHeight: Int = 100
        ) {
            val cardViewParams = LinearLayout.LayoutParams(widthParam, heightParam)

            card.layoutParams = cardViewParams
            margins?.let {
                (card.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                    margins[0],
                    margins[1],
                    margins[2],
                    margins[3]
                )
            }

            minHeight?.let {
                card.minimumHeight = minHeight
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

        fun getChildrenRecursive(view: View): MutableList<View> {
            val mutableList = mutableListOf(view)
            if (view !is ViewGroup)
                return mutableList

            view.children.forEach {
                mutableList.addAll(getChildrenRecursive(it))
            }
            return mutableList
        }

    }
}