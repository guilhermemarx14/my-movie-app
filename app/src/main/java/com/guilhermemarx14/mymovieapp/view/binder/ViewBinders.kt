package com.guilhermemarx14.mymovieapp.view.binder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.guilhermemarx14.mymovieapp.R
import com.guilhermemarx14.mymovieapp.model.WatchProvider
import com.guilhermemarx14.mymovieapp.util.Util
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@BindingAdapter("srcUrl")
fun ImageView.bindSrcUrl(
    url: String?
) {
    Glide
        .with(this)
        .load(url ?: "")
        .centerInside()
        .into(this)
}

@BindingAdapter("imageList")
fun ImageCarousel.imageList(imageList: List<CarouselItem>?) {
    imageList?.let {
        this.setData(imageList)
    }
}

@BindingAdapter("cardList")
fun FlexboxLayout.cardList(cardList: List<WatchProvider>?) {
    this.removeAllViews()
    cardList?.distinctBy { it.provider_id }?.forEach { provider ->
        val providerCard = CardView(this.context)

        Util.configureCardLayout(
            card = providerCard,
            margins = listOf(0, 0, 0, 20),
            widthParam = ViewGroup.LayoutParams.WRAP_CONTENT,
            heightParam = ViewGroup.LayoutParams.WRAP_CONTENT
        )

        Util.configureCardAppearance(providerCard, 60F, 20F)

        val imageView = ImageView(this.context)

        imageView.layoutParams = LinearLayout.LayoutParams(100, 100)
        imageView.layoutParams.width *= 2
        imageView.layoutParams.height *= 2

        imageView.bindSrcUrl(provider.getLogoPath())

        providerCard.addView(imageView)

        this.addView(providerCard)
    }
}

@BindingAdapter("isListItem")
fun CardView.isListItem(isListItem: Boolean?) {
    isListItem?.let {
        if (!it) return
    }

    Util.configureCardLayout(
        card = this,
        margins = listOf(20, 10, 20, 10),
        widthParam = ViewGroup.LayoutParams.MATCH_PARENT,
        heightParam = ViewGroup.LayoutParams.WRAP_CONTENT,
        minHeight = 200
    )

    Util.configureCardAppearance(this, 30F, 20F)

    val children = Util.getChildrenRecursive(this)
    children.forEach {
        it.setBackgroundColor(ContextCompat.getColor(this.context, R.color.primaryColor))
    }
}

