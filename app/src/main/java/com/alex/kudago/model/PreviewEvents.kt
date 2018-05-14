package com.alex.kudago.model

import com.alex.kudago.model.event.Event
import com.alex.kudago.model.event.Image
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class PreviewEvents(var count: Int,
                         @SerializedName("next")
                         var nextPage: String,
                         @SerializedName("results")
                         var events: ArrayList<Event>)
