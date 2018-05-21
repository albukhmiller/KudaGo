package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 10.05.2018.
 */


data class Image(@SerializedName("image") var urlImage: String)