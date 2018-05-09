package com.alex.kudago.model.api

import com.alex.kudago.model.City
import com.alex.kudago.model.PreviewEvents
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by alex on 08.05.2018.
 */

interface ServerKudaGoApi {

    @GET("public-api/v1.2/locations/?lang=ru")
    fun getCitiesList(): Call<ArrayList<City>>

    @GET("public-api/v1.4/events/?expand=place,images&fields=description,is_free,price,dates,place,title,images&text_format=text")
    fun getEvents(@Query("location") slug: String): Call<PreviewEvents>
}