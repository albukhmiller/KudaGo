package com.alex.kudago.presentations.ui.recyclerView.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.kudago.R
import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.presentations.ui.recyclerView.holders.EventsHolder

/**
 * Created by alex on 09.05.2018.
 */
class EventsAdapter(private val events: List<CacheEvent>, private val listener: (CacheEvent) -> Unit) : RecyclerView.Adapter<EventsHolder>() {

    private val VIEW_BODY = 0
    private val VIEW_FOOTER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)

        when (viewType) {
            VIEW_BODY -> return EventsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false), true)
            VIEW_FOOTER -> return EventsHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_progress_bar, parent, false), false)
        }
        return EventsHolder(v, true)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventsHolder, position: Int) = holder.bind(events[position], listener)

    override fun getItemViewType(position: Int): Int {
        if (position == events.size - 1)
            return VIEW_FOOTER

        return VIEW_BODY
    }
}