package com.alex.kudago.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alex on 08.05.2018.
 */
data class City(var slug: String, var name: String, var isSelected: Boolean = false, var position: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(slug)
        writeString(name)
        writeInt((if (isSelected) 1 else 0))
        writeInt(position)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<City> = object : Parcelable.Creator<City> {
            override fun createFromParcel(source: Parcel): City = City(source)
            override fun newArray(size: Int): Array<City?> = arrayOfNulls(size)
        }
    }
}