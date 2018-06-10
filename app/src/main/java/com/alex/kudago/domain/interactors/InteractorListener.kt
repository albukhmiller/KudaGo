package com.alex.kudago.domain.interactors

import com.alex.kudago.model.City

/**
 * Created by alex on 21.05.2018.
 */
interface InteractorListener {

    fun <T> onSuccessLoad(data: T)
    fun onFailureLoad()
}