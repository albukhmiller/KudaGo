package com.alex.kudago.domain

import android.util.Log
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.model.api.ServerKudaGoApi
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by alex on 09.05.2018.
 */
class LoaderEventsIntractor @Inject constructor(private val serverApi: ServerKudaGoApi) {


    private var listener: InteractorListener? = null

    fun setListener(listener: InteractorListener) {
        this.listener = listener
    }

    fun loadEvents(slug: String) {
        serverApi.getEvents(slug)
                .enqueue(object : retrofit2.Callback<PreviewEvents> {
                    override fun onFailure(call: Call<PreviewEvents>?, t: Throwable?) {
                        listener?.onFailureLoadPreviewEvents()
                    }

                    override fun onResponse(call: Call<PreviewEvents>?, response: Response<PreviewEvents>?) {
                        listener?.onSuccessLoadPreviewEvents(response?.body()!!)
                    }
                })
    }
}