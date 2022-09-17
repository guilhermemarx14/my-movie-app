package com.guilhermemarx14.mymovieapp.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.guilhermemarx14.mymovieapp.R

class ViewUtil {

    companion object{
        fun configureCardAppearance(card: CardView, radius: Float, elevation: Float) {
            card.radius = radius
            card.cardElevation = elevation
        }

        fun configureCardLayout(
            card: CardView,
            margins: List<Int>?,
            widthParam: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            heightParam: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
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

            card.minimumHeight = minHeight
        }
    }
}