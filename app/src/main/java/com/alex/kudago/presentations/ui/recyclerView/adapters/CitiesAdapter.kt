package com.alex.kudago.presentations.ui.recyclerView.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alex.kudago.R
import com.alex.kudago.model.City
import com.alex.kudago.presentations.ui.recyclerView.holders.CitiesHolder

/**
 * Created by alex on 08.05.2018.
 */
class CitiesAdapter(private val cities: ArrayList<City>, private val currentCity: String,
                    private val listener: (City) -> Unit) : RecyclerView.Adapter<CitiesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CitiesHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cities, parent, false))

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) = holder.bind(cities[position], currentCity, listener)

}