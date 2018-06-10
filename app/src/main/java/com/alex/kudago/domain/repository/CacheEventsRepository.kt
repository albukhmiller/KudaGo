package com.alex.kudago.domain.repository

import android.os.AsyncTask
import com.alex.kudago.data.database.CacheEvent
import com.alex.kudago.data.database.EventDao
import com.alex.kudago.data.database.MapperEvents
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.model.api.ServerKudaGoApi
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by alex on 28.05.2018.
 */
class CacheEventsRepository @Inject constructor(private val serverApi: ServerKudaGoApi,
                                                private val eventDao: EventDao) {


    private var listener: CacheEventsRepositoryListener? = null

    private var urlNextPage: String? = null

    fun setListener(listener: CacheEventsRepositoryListener) {
        this.listener = listener
    }

    fun saveEventToCache(event: List<CacheEvent>) =
            object : AsyncTask<Unit, Unit, Unit>() {

                override fun doInBackground(vararg params: Unit?) = eventDao.addEvents(event)
            }.execute()

    fun clearCache(slug: String) =
            object : AsyncTask<Unit, Unit, Unit>() {
                override fun doInBackground(vararg params: Unit?) = eventDao.clearCache()

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    loadEvents(FIRST_PAGE_URL, slug)
                }
            }.execute()

    fun loadFromCache() = eventDao.findAll()

    fun loadEvents(url: String, slug: String) {
        serverApi.getEvents(url, slug)
                .enqueue(object : retrofit2.Callback<PreviewEvents> {
                    override fun onFailure(call: Call<PreviewEvents>?, t: Throwable?) {
                        listener?.onFailureLoadEvents()
                    }

                    override fun onResponse(call: Call<PreviewEvents>?, response: Response<PreviewEvents>?) {
                        listener?.onSuccessLoadEvents(MapperEvents.convertEventToCashEvent(response?.body()!!), response?.body()!!.nextPage)
                    }
                })
    }
}