package com.alex.kudago.presentations.presenters

import com.alex.kudago.presentations.views.CitiesView

/**
 * Created by alex on 08.05.2018.
 */
interface CitiesPresenter : BaseMvpPresenter<CitiesView> {

    fun onLoadCitiesList()
}