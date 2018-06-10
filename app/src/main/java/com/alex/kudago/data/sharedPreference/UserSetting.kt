package com.alex.kudago.data.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by alex on 17.05.2018.
 */
class UserSetting @Inject constructor(private val context: Context) {

    private val NAME_SHARED_PREFERENCE = "UserSetting"
    private val CITY_FIELD = "city"
    private val SLUG_FIELD = "slug"

    private var currentCity: SharedPreferences? = null


    init {
        currentCity = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE)
    }


    fun saveCity(City: String) {
        val editer = currentCity?.edit()
        editer?.putString(CITY_FIELD, City)
        editer?.commit()
    }

    fun saveSlug(slug: String) {
        val editer = currentCity?.edit()
        editer?.putString(SLUG_FIELD, slug)
        editer?.commit()
    }

    fun getCity() = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE).getString(CITY_FIELD, "")

    fun getSlug() = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE).getString(SLUG_FIELD, "")

}