package com.alex.kudago.domain

import com.alex.kudago.model.event.Event

/**
 * Created by alex on 14.05.2018.
 */
interface LoadDetailEventInteractorListener {

    fun onSuccessLoadDetailEvent(event: Event)
    fun onFailureLoadDetailEvent()
}