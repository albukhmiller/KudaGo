package com.alex.kudago.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


/**
 * Created by alex on 16.05.2018.
 */
@Entity(tableName = "event")
data class CacheEvent(@PrimaryKey(autoGenerate = true)
                      var keyId: Long = 0,
                      var id: Long,
                      var date: String,
                      var title: String,
                      var address: String?,
                      var lat: Double?,
                      var lnt: Double?,
                      var description: String,
                      var isFree: Boolean,
                      var image: String,
                      var price: String,
                      var next: String) : Parcelable {


    constructor(source: Parcel) : this(
            source.readLong(),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(keyId)
        writeLong(id)
        writeString(date)
        writeString(title)
        writeString(address)
        writeValue(lat)
        writeValue(lnt)
        writeString(description)
        writeInt((if (isFree) 1 else 0))
        writeString(image)
        writeString(price)
        writeString(next)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CacheEvent> = object : Parcelable.Creator<CacheEvent> {
            override fun createFromParcel(source: Parcel): CacheEvent = CacheEvent(source)
            override fun newArray(size: Int): Array<CacheEvent?> = arrayOfNulls(size)
        }
    }

}

