package com.alex.kudago.presentations.ui.recyclerView.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alex.kudago.model.event.Event
import com.alex.kudago.utlis.TimeConverter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_events.view.*

/**
 * Created by alex on 09.05.2018.
 */
class EventsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Event, listener : (Event) -> Unit) {
        itemView.tvNameEvent.text = item.title

        itemView.setOnClickListener({listener(item)})

        if (item.place != null)
            itemView.tvLocation.text = item.place?.address
        else itemView.tvLocation.visibility = View.GONE

        itemView.tvPreview.text = item.description

        if (!item.price.isEmpty())
            itemView.tvMoney.text = item.price
        else itemView.tvMoney.visibility = View.GONE

        loadPhotoPreview(item.images[0].urlImage)

        var timeConverter = TimeConverter()
        timeConverter.convertTime(item.dates[0].startDate, item.dates[0].endDate)
        itemView.tvToday.text = timeConverter.convertTime(item.dates[0].startDate, item.dates[0].endDate)
    }

    private fun loadPhotoPreview(url: String) {
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(itemView.imgImagePreview)
    }
}