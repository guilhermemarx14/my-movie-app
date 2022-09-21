package com.guilhermemarx14.mymovieapp.view.binder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.guilhermemarx14.mymovieapp.model.WatchProvider
import com.guilhermemarx14.mymovieapp.util.ViewUtil
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

        ViewUtil.configureCardLayout(
            card = providerCard,
            margins = listOf(0, 0, 0, 20),
            widthParam = ViewGroup.LayoutParams.WRAP_CONTENT,
            heightParam = ViewGroup.LayoutParams.WRAP_CONTENT
        )

        ViewUtil.configureCardAppearance(providerCard, 60F, 20F)

        val imageView = ImageView(this.context)

        imageView.layoutParams = LinearLayout.LayoutParams(100, 100)
        imageView.layoutParams.width *= 2
        imageView.layoutParams.height *= 2

        imageView.bindSrcUrl(provider.getLogoPath())

        providerCard.addView(imageView)

        this.addView(providerCard)
    }
}


