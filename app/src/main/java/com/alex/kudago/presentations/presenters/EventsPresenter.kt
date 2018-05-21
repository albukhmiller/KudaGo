package com.alex.kudago.presentations.presenters

import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.presentations.views.EventsView

/**
 * Created by alex on 07.05.2018.
 */
interface EventsPresenter : BaseMvpPresenter<EventsView> {

    fun onLoadEvents(url: String?, slug: String)

    fun onLoadDataOfCache(): List<CacheEvent>

    fun onSaveEventCache(event: List<CacheEvent>)

    fun onClearCache()

    fun getUserCity(): MutableList<String>?

    fun onSaveUserCity(city: String, slug: String)

    fun onChangeCity(slug: String)
}