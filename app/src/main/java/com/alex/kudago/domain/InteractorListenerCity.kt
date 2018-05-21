package com.alex.kudago.domain

import com.alex.kudago.model.City

/**
 * Created by alex on 21.05.2018.
 */
interface InteractorListenerCity {

    fun onSuccessLoadCitiesList(cities: ArrayList<City>)
    fun onFailureLoadCitiesList()
}