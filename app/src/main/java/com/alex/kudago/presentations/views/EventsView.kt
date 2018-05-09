package com.alex.kudago.presentations.views

import com.alex.kudago.model.PreviewEvents

/**
 * Created by alex on 07.05.2018.
 */
interface EventsView: BaseMvpView {

    fun onSuccessLoadPreviewEvents(events: PreviewEvents)
    fun onFailureLoadPreviewEvents()
}