package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.presentations.presenters.EventsPresenter
import com.alex.kudago.presentations.views.EventsView
import javax.inject.Inject

/**
 * Created by alex on 07.05.2018.
 */
class EventsPresenterImpl @Inject constructor(): BaseMvpPresenterImpl<EventsView>(), EventsPresenter {
}