package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alex on 09.05.2018.
 */
data class Coordinates(var lat: String,
                       var lon: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(lat)
        writeString(lon)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Coordinates> = object : Parcelable.Creator<Coordinates> {
            override fun createFromParcel(source: Parcel): Coordinates = Coordinates(source)
            override fun newArray(size: Int): Array<Coordinates?> = arrayOfNulls(size)
        }
    }
}