package com.alex.kudago.domain

import com.alex.kudago.model.City
import com.alex.kudago.model.api.ServerKudaGoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by alex on 08.05.2018.
 */
class LoaderCitiesInteractor @Inject constructor(private val serverApi: ServerKudaGoApi) {

    private var listener: InteractorListenerCity? = null

    fun setInteractorListener(listener: InteractorListenerCity) {
        this.listener = listener
    }

    fun loadCitiesList() {
        serverApi.getCitiesList()
                .enqueue(object : Callback<ArrayList<City>> {
                    override fun onFailure(call: Call<ArrayList<City>>?, t: Throwable?) {
                        listener?.onFailureLoadCitiesList()
                    }

                    override fun onResponse(call: Call<ArrayList<City>>?, response: Response<ArrayList<City>>?) {
                        listener?.onSuccessLoadCitiesList(response?.body()!!)
                    }
                })

    }
}