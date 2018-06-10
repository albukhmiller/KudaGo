package com.alex.kudago.model.event

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Date(var id: Long,
                @SerializedName("start") var startDate: String,
                @SerializedName("end") var endDate: String)