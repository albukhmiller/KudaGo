package com.alex.kudago.model.event

import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Event(var next: String,
                 var dates: ArrayList<Date>,
                 var title: String,
                 var place: Place,
                 var description: String,
                 @SerializedName("is_free")
                 var isFree: Boolean,
                 var images: ArrayList<Image>,
                 var price: String)