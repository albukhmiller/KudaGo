package com.alex.kudago.utlis

import android.content.Context
import android.widget.ImageView
import com.alex.kudago.App
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService
import javax.inject.Inject

/**
 * Created by alex on 12.05.2018.
 */
class LoaderImage: ImageLoadingService {

    override fun loadImage(url: String?, imageView: ImageView?) {
        Picasso.get().load(url).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.get().load(resource).into(imageView)
    }

    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
        Picasso.get().load(url).
                placeholder(placeHolder)
                .error(errorDrawable)
                .into(imageView)
    }
}