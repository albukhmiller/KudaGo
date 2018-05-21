package com.alex.kudago.domain

import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.model.City

/**
 * Created by alex on 08.05.2018.
 */
interface InteractorListenerEvent {

    fun onSuccessLoadPreviewEvents(events: List<CacheEvent>)
    fun onFailureLoadPreviewEvents()
}