package com.alex.kudago.presentations.ui.imageSlider

import com.alex.kudago.R
import com.alex.kudago.model.event.Image
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

/**
 * Created by alex on 12.05.2018.
 */
class SliderImageAdapter(var images: ArrayList<Image>) : SliderAdapter() {

    override fun getItemCount() = images.size

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) = imageSlideViewHolder?.bindImageSlide(images[position].urlImage, R.drawable.ic_placeholder, R.drawable.ic_placeholder)!!
}