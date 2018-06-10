package com.alex.kudago.presentations.views

import com.alex.kudago.model.event.Event

/**
 * Created by alex on 14.05.2018.
 */
interface DetailEventView : BaseMvpView {

    fun onSuccessLoadDetailEvent(event: Event)
    fun onFailureLoadDetailEvent()
}