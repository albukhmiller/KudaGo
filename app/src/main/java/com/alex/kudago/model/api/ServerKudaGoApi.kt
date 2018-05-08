package com.alex.kudago.model.api

import com.alex.kudago.model.City
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by alex on 08.05.2018.
 */

interface ServerKudaGoApi {

    @GET("public-api/v1.2/locations/?lang=ru")
    fun getCitiesList(): Call<ArrayList<City>>
}