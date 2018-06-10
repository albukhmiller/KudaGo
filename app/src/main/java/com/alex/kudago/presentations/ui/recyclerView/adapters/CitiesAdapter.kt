package com.alex.kudago.presentations.ui.recyclerView.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.presentations.ui.recyclerView.holders.CitiesHolder
import kotlinx.android.synthetic.main.item_cities.view.*

/**
 * Created by alex on 08.05.2018.
 */
class CitiesAdapter(private val cities: ArrayList<City>, private val currentCity: String,
                    private val listener: (City) -> Unit) : RecyclerView.Adapter<CitiesHolder>() {

    private var currentPositionCity: Int? = null

    init {
        currentPositionCity = getPositionCurrentCity()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CitiesHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cities, parent, false))

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {

        if (cities[position].isSelected) {
            holder.itemView.btnCity.setCompoundDrawablesWithIntrinsicBounds(null, null, holder.itemView.resources.getDrawable(R.drawable.ic_select_city, null), null)
        }
        holder.itemView.btnCity.text = cities[position].name
        holder.itemView.btnCity.setOnClickListener({
            listener(cities[position])
            cities[currentPositionCity!!].isSelected = false
            cities[position].isSelected = true
            notifyDataSetChanged()
        })
    }

    private fun getPositionCurrentCity(): Int {

        for (i in cities.indices)
            if (cities[i].name == currentCity) {
                currentPositionCity = i
                cities[i].position = i
                cities[i].isSelected = true
                return i
            }
        return -1
    }


}