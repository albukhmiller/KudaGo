package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Event(var id: Long,
                 var dates: List<Date>,
                 var title: String,
                 var place: Place?,
                 var description: String,
                 @SerializedName("is_free")
                 var isFree: Boolean,
                 var images: ArrayList<Image>,
                 var price: String,
                 @SerializedName("body_text")
                 var subscription: String?)