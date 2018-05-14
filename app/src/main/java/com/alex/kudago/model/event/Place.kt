package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alex on 09.05.2018.
 */
data class Place(var address: String,
                 var coords: Coordinates) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<Coordinates>(Coordinates::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(address)
        writeParcelable(coords, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Place> = object : Parcelable.Creator<Place> {
            override fun createFromParcel(source: Parcel): Place = Place(source)
            override fun newArray(size: Int): Array<Place?> = arrayOfNulls(size)
        }
    }
}