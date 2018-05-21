package com.alex.kudago.domain

import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.model.api.ServerKudaGoApi
import com.alex.kudago.utlis.TimeConverter
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by alex on 09.05.2018.
 */
class LoaderEventsIntractor @Inject constructor(private val serverApi: ServerKudaGoApi) {


    private var listener: InteractorListenerEvent? = null

    fun setListener(listener: InteractorListenerEvent) {
        this.listener = listener
    }

    fun loadEvents(url: String, slug: String) {
        serverApi.getEvents(url, slug)
                .enqueue(object : retrofit2.Callback<PreviewEvents> {
                    override fun onFailure(call: Call<PreviewEvents>?, t: Throwable?) {

                        listener?.onFailureLoadPreviewEvents()
                    }

                    override fun onResponse(call: Call<PreviewEvents>?, response: Response<PreviewEvents>?) {
                        convertEventToCashEvent(response?.body()!!)
                        listener?.onSuccessLoadPreviewEvents(convertEventToCashEvent(response?.body()!!))
                    }
                })
    }

    private fun convertEventToCashEvent(previewEvents: PreviewEvents): MutableList<CacheEvent> {
        var timeConverter = TimeConverter()
        var events = previewEvents.events
        var cacheEvent = ArrayList<CacheEvent>()

        for (event in events) {
            var evCache = CacheEvent(id = event.id,
                    date = timeConverter.convertSrtingToDate(event.dates[0].startDate, event.dates[0].endDate),
                    title = event.title,
                    address = event.place?.address,
                    lat = event.place?.coords?.lat,
                    lnt = event.place?.coords?.lon,
                    description = event.description,
                    isFree = event.isFree,
                    image = event.images[0].urlImage,
                    price = event.price,
                    next = previewEvents.nextPage)
            cacheEvent.add(evCache)
        }
        return cacheEvent
    }
}