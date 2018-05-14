package com.alex.kudago.presentations.ui.recyclerView.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alex.kudago.R
import com.alex.kudago.model.event.Event
import com.alex.kudago.presentations.ui.recyclerView.holders.EventsHolder

/**
 * Created by alex on 09.05.2018.
 */
class EventsAdapter(private val events: ArrayList<Event>) : RecyclerView.Adapter<EventsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventsHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_events, parent, false))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) = holder.bind(events[position])

}