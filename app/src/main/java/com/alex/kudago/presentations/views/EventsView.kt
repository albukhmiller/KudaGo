package com.alex.kudago.presentations.views

import com.alex.kudago.data.database.CacheEvent

/**
 * Created by alex on 07.05.2018.
 */
interface EventsView : BaseMvpView {

    fun onSuccessLoadPreviewEvents(events: List<CacheEvent>)
    fun onFailureLoadPreviewEvents()
}