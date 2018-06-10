package com.alex.kudago.domain.interactors

import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.domain.repository.CacheEventsRepository
import com.alex.kudago.domain.repository.CacheEventsRepositoryListener
import com.alex.kudago.domain.repository.FIRST_PAGE_URL
import com.alex.kudago.model.City
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by alex on 09.05.2018.
 */
@Singleton
class LoaderEventsInteractor @Inject constructor(private val cacheEventRepository: CacheEventsRepository) : CacheEventsRepositoryListener {

    private var nextPageUrl: String? = null

    init {
        cacheEventRepository.setListener(this)
        nextPageUrl = FIRST_PAGE_URL
    }

    private var listener: InteractorListener? = null

    fun setInteractorListener(listener: InteractorListener) {
        this.listener = listener
    }


    fun cachedEvents(events: List<CacheEvent>) = cacheEventRepository.saveEventToCache(events)

    fun clearCache(slug: String) = cacheEventRepository.clearCache(slug)

    fun loadDataFromCache() = cacheEventRepository.loadFromCache()

    fun loadEventsFromServer(slug: String) = cacheEventRepository.loadEvents(nextPageUrl!!, slug)


    override fun onSuccessLoadEvents(events: List<CacheEvent>, next: String) {
        nextPageUrl = next
        listener?.onSuccessLoad(events)
    }

    override fun onFailureLoadEvents() {
        listener?.onFailureLoad()
    }
}
