package com.alex.kudago.domain

import com.alex.kudago.model.City

/**
 * Created by alex on 08.05.2018.
 */
interface InteractorListener {

    fun onSuccessLoadCitiesList(cities: ArrayList<City>)
    fun onFailureLoadCitiesList()
}