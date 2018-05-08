package com.alex.kudago.presentations.presentersImpls

import com.alex.kudago.domain.InteractorListener
import com.alex.kudago.domain.LoaderCitiesInteractor
import com.alex.kudago.model.City
import com.alex.kudago.presentations.presenters.CitiesPresenter
import com.alex.kudago.presentations.views.CitiesView
import javax.inject.Inject

/**
 * Created by alex on 08.05.2018.
 */
class CitiesPresenterImpl @Inject constructor(private val loaderCitiesInteractor: LoaderCitiesInteractor) : BaseMvpPresenterImpl<CitiesView>(), CitiesPresenter, InteractorListener {

    init {
        loaderCitiesInteractor.setInteractorListener(this)
    }

    override fun onLoadCitiesList() {
        loaderCitiesInteractor.loadCitiesList()
    }

    override fun onSuccessLoadCitiesList(cities: ArrayList<City>) {
        mView?.onSuccessLoadCitiesList(cities)
    }

    override fun onFailureLoadCitiesList() {
        mView?.onFailureLoadCitiesList()
    }
}