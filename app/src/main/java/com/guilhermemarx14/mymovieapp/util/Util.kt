package com.guilhermemarx14.mymovieapp.util

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

class Util {
    companion object{
        fun configureCardAppearance(card: CardView, radius: Float, elevation: Float) {
            card.radius = radius
            card.cardElevation = elevation
        }

        fun configureCardLayout(card: CardView, margins :List<Int>?) {
            val cardViewParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            card.layoutParams = cardViewParams
            margins?.let {
                (card.layoutParams as ViewGroup.MarginLayoutParams).setMargins(margins[0],margins[1],margins[2],margins[3])
            }

        }
    }
}