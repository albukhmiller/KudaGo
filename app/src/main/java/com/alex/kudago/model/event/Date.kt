package com.alex.kudago.model.event

import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 09.05.2018.
 */
data class Date(@SerializedName("start") var startDate: String,
                @SerializedName("end") var endDate: String)