package com.alex.kudago.model.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Event(var id: Long,
                 var next: String?,
                 var dates: ArrayList<Date>,
                 var title: String,
                 var place: Place?,
                 var description: String,
                 @SerializedName("is_free")
                 var isFree: Boolean,
                 var images: ArrayList<Image>,
                 var price: String,
                 @SerializedName("body_text")
                 var subscription: String?) : Parcelable
{
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.createTypedArrayList(Date.CREATOR),
            source.readString(),
            source.readParcelable<Place>(Place::class.java.classLoader),
            source.readString(),
            1 == source.readInt(),
            source.createTypedArrayList(Image.CREATOR),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(next)
        writeTypedList(dates)
        writeString(title)
        writeParcelable(place, 0)
        writeString(description)
        writeInt((if (isFree) 1 else 0))
        writeTypedList(images)
        writeString(price)
        writeString(subscription)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Event> = object : Parcelable.Creator<Event> {
            override fun createFromParcel(source: Parcel): Event = Event(source)
            override fun newArray(size: Int): Array<Event?> = arrayOfNulls(size)
        }
    }
}