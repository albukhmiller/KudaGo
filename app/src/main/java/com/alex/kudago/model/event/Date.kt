package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Date(@SerializedName("start") var startDate: String,
                @SerializedName("end") var endDate: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(startDate)
        writeString(endDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Date> = object : Parcelable.Creator<Date> {
            override fun createFromParcel(source: Parcel): Date = Date(source)
            override fun newArray(size: Int): Array<Date?> = arrayOfNulls(size)
        }
    }
}