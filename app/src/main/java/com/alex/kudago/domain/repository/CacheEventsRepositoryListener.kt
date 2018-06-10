package com.alex.kudago.domain.repository

import com.alex.kudago.data.database.CacheEvent

/**
 * Created by alex on 29.05.2018.
 */
interface CacheEventsRepositoryListener {

    fun onSuccessLoadEvents(events: List<CacheEvent>, nextPageUrl: String)
    fun onFailureLoadEvents()
}