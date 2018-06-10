package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.data.database.EventDao
import com.alex.kudago.data.sharedPreference.UserSetting
import com.alex.kudago.domain.interactors.InteractorListener
import com.alex.kudago.domain.interactors.LoaderEventsInteractor
import com.alex.kudago.domain.repository.FIRST_PAGE_URL
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView
import javax.inject.Inject

/**
 * Created by alex on 07.05.2018.
 */
class EventsPresenterImpl @Inject constructor(private val loaderEventsIntractor: LoaderEventsInteractor,
                                              private val eventDao: EventDao,
                                              private val setting: UserSetting) : BaseMvpPresenterImpl<EventsView>(), EventsPresenter, InteractorListener {



    init {
        loaderEventsIntractor.setInteractorListener(this)
    }

    override fun onLoadEvents(slug: String) = loaderEventsIntractor.loadEventsFromServer(slug)

    override fun <T> onSuccessLoad(events: T) {
        onSaveEventCache(events as List<CacheEvent>)
        mView?.onSuccessLoadPreviewEvents(events)
    }

    override fun onFailureLoad() {
        mView?.onFailureLoadPreviewEvents()
    }

    override fun onLoadDataOfCache() = loaderEventsIntractor.loadDataFromCache()

    override fun onSaveEventCache(event: List<CacheEvent>) {
        loaderEventsIntractor.cachedEvents(event)
    }

    override fun onClearCache() {
        eventDao.clearCache()
    }

    override fun getUserCity(): MutableList<String> = mutableListOf(setting.getCity(), setting.getSlug())

    override fun onSaveUserCity(city: String, slug: String) {
        setting.saveCity(city)
        setting.saveSlug(slug)
    }

    override fun onChangeCity(slug: String) {
        clearCache(slug)
    }

    private fun clearCache(slug: String) {
        loaderEventsIntractor.clearCache(slug)
    }
}