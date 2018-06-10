package com.alex.kudago.model.api

import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import com.alex.kudago.model.event.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by alex on 08.05.2018.
 */

interface ServerKudaGoApi {

    @GET("locations/?lang=ru")
    fun getCitiesList(): Call<ArrayList<City>>

    @GET
    fun getEvents(@Url url: String, @Query("location") slug: String): Call<PreviewEvents>

    @GET("events/{id}/?fields=body_text,images&expand=images&text_format=text")
    fun getDetailEvent(@Path("id") id: Long): Call<Event>

}