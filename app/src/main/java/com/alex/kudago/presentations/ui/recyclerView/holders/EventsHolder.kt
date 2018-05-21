package com.alex.kudago.presentations.ui.recyclerView.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alex.kudago.R
import com.alex.kudago.data.database.CacheEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_events.view.*

/**
 * Created by alex on 09.05.2018.
 */
class EventsHolder(itemView: View, private val isBody: Boolean) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: CacheEvent, listener: (CacheEvent) -> Unit) {

        if (isBody) {
            itemView.tvNameEvent.text = item.title

            itemView.setOnClickListener({ listener(item) })


            if (!item.price.isEmpty())
                itemView.tvMoney.text = item.price
            else itemView.tvMoney.visibility = View.GONE

            if (item.address != null)
                itemView.tvLocation.text = item.address
            else itemView.tvLocation.visibility = View.GONE

            itemView.tvPreview.text = item.description

            loadPhotoPreview(item.image)

            itemView.tvToday.text = item.date

        }
    }

    private fun loadPhotoPreview(url: String) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .fit()
                .centerCrop()
                .error(R.drawable.ic_placeholder)
                .into(itemView.imgImagePreview)
    }
}