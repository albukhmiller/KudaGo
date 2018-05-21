package com.alex.kudago.presentations.presentersImpls

import android.os.AsyncTask
import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.data.database.EventDao
import com.alex.kudago.data.sharedPreference.UserSetting
import com.alex.kudago.domain.InteractorListenerEvent
import com.alex.kudago.domain.LoaderEventsIntractor
import com.alex.kudago.model.City
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView
import javax.inject.Inject

/**
 * Created by alex on 07.05.2018.
 */
class EventsPresenterImpl @Inject constructor(private val loaderEventsIntractor: LoaderEventsIntractor,
                                              private val eventDao: EventDao,
                                              private val setting: UserSetting) : BaseMvpPresenterImpl<EventsView>(), EventsPresenter,
        InteractorListenerEvent {

    private val FIRST_PAGE_URL = "events/?expand=place,images&fields=description,is_free,id,price,dates,place,title,images&text_format=text"

    init {
        loaderEventsIntractor.setListener(this)
    }

    override fun onLoadEvents(url: String?, slug: String) {
        if (url.isNullOrEmpty())
            loaderEventsIntractor.loadEvents(FIRST_PAGE_URL, slug)
        else
            loaderEventsIntractor.loadEvents(url!!, slug)
    }

    override fun onSuccessLoadPreviewEvents(events: List<CacheEvent>) {
        onSaveEventCache(events)
        mView?.onSuccessLoadPreviewEvents(events)
    }

    override fun onFailureLoadPreviewEvents() {
        mView?.onFailureLoadPreviewEvents()
    }

    override fun onLoadDataOfCache() = eventDao.findAll()

    override fun onSaveEventCache(event: List<CacheEvent>) {
        object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) = eventDao.addEvents(event)
        }.execute()
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
        object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) = onClearCache()

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                onLoadEvents(FIRST_PAGE_URL, slug)
            }
        }.execute()
    }
}