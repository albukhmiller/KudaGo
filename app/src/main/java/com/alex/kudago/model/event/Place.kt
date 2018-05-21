package com.alex.kudago.model.event

import android.arch.persistence.room.Entity
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by alex on 09.05.2018.
 */
data class Place(var address: String,
                 var coords: Coordinates)