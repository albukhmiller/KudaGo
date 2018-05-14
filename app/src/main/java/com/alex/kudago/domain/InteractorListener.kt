package com.alex.kudago.domain

import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents

/**
 * Created by alex on 08.05.2018.
 */
interface InteractorListener {

    fun onSuccessLoadCitiesList(cities: ArrayList<City>)
    fun onFailureLoadCitiesList()

    fun onSuccessLoadPreviewEvents(events: PreviewEvents)
    fun onFailureLoadPreviewEvents()
}