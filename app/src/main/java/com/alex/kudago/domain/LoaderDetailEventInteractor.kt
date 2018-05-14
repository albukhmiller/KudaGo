package com.alex.kudago.domain

import com.alex.kudago.model.api.ServerKudaGoApi
import com.alex.kudago.model.event.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by alex on 14.05.2018.
 */
class LoaderDetailEventInteractor @Inject constructor(private val serverApi: ServerKudaGoApi) {

    private var listener: LoadDetailEventInteractorListener? = null


    fun setLoaderDetailEventInteractorListener(listener: LoadDetailEventInteractorListener) {
        this.listener = listener
    }

    fun getDetailEvent(id: Long) {

        serverApi.getDetailEvent(id).enqueue(object : Callback<Event> {

            override fun onFailure(call: Call<Event>?, t: Throwable?) {
                listener?.onFailureLoadDetailEvent()
            }

            override fun onResponse(call: Call<Event>?, response: Response<Event>?) {
                listener?.onSuccessLoadDetailEvent(response?.body()!!)
            }
        })
    }
}