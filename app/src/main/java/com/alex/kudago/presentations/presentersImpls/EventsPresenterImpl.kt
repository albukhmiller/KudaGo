package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.domain.InteractorListener
import com.alex.kudago.domain.LoaderEventsIntractor
import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView
import javax.inject.Inject

/**
 * Created by alex on 07.05.2018.
 */
class EventsPresenterImpl @Inject constructor(private val loaderEventsIntractor: LoaderEventsIntractor) : BaseMvpPresenterImpl<EventsView>(), EventsPresenter,
        InteractorListener {

    init {
        loaderEventsIntractor.setListener(this)
    }

    override fun onLoadEvents(slug : String) {
        loaderEventsIntractor.loadEvents(slug)
    }

    override fun onSuccessLoadPreviewEvents(events: PreviewEvents) {
        mView?.onSuccessLoadPreviewEvents(events)
    }

    override fun onFailureLoadPreviewEvents() {
        mView?.onFailureLoadPreviewEvents()
    }

    override fun onSuccessLoadCitiesList(cities: ArrayList<City>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailureLoadCitiesList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}