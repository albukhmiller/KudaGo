package com.alex.kudago.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alex on 08.05.2018.
 */
data class City(var slug: String, var name: String) : Parcelable {

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(slug)
        dest?.writeString(name)
    }

    override fun describeContents() = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())


    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}