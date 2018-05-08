package com.alex.kudago.presentations.ui.recyclerView.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.alex.kudago.R
import com.alex.kudago.model.City
import kotlinx.android.synthetic.main.item_cities.view.*

/**
 * Created by alex on 08.05.2018.
 */
class CitiesHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {

    fun bind(item: City, currentCity: String, listener: (City) -> Unit) {
        if (currentCity == item.name)
            itemView.btnCity.setCompoundDrawablesWithIntrinsicBounds(null, null, itemView.resources.getDrawable(R.drawable.ic_select_city, null), null)
        itemView.btnCity.text = item.name
        itemView.btnCity.setOnClickListener({
            listener(item)
            itemView.btnCity.setCompoundDrawablesWithIntrinsicBounds(null, null, itemView.resources.getDrawable(R.drawable.ic_select_city, null), null)
        })

    }
}