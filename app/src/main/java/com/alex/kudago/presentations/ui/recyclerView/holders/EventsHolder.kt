package com.alex.kudago.presentations.ui.recyclerView.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alex.kudago.model.event.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_events.view.*

/**
 * Created by alex on 09.05.2018.
 */
class EventsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Event) {
        itemView.tvNameEvent.text = item.title
        if (item.place != null)
            itemView.tvLocation.text = item.place.address
        itemView.tvPreview.text = item.description
        itemView.tvMoney.text = item.price
        loadPhotoPreview(item.images[0].urlImage)
    }

    private fun loadPhotoPreview(url: String) {
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(itemView.imgImagePreview)
    }
}