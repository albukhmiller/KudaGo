package com.alex.kudago.presentations.views

import com.alex.kudago.model.City

/**
 * Created by alex on 08.05.2018.
 */
interface CitiesView : BaseMvpView {

    fun onSuccessLoadCitiesList(cities: ArrayList<City>)
    fun onFailureLoadCitiesList()
}