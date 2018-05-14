package com.alex.kudago.utlis

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by alex on 11.05.2018.
 */
class TimeConverter {

    fun convertTime(start: String?, end: String?): String {

        val formatter = SimpleDateFormat("dd MMMM")
        val calendarStart = Calendar.getInstance()
        val calendarEnd = Calendar.getInstance()

        var startTime = formatter.format(Date(start?.toLong()!! * 1000))
        var endTime = formatter.format(Date(end?.toLong()!! * 1000))

        calendarStart.time = formatter.parse(startTime)
        calendarEnd.time = formatter.parse(endTime)
        if (compareDayTo(calendarStart, calendarEnd))
            return startTime
        if (compareMonthTo(calendarStart.get(Calendar.MONTH), calendarEnd.get(Calendar.MONTH))) {

            return "${calendarStart[Calendar.DAY_OF_MONTH]}-${calendarEnd[Calendar.DAY_OF_MONTH]} " +
                    "${SimpleDateFormat("MMMM").format(calendarStart[Calendar.MONTH])}"
        }
        return "$startTime - $endTime"
    }

    private fun compareMonthTo(month1: Int, month2: Int): Boolean {
        if (month1 == month2)
            return true
        return false
    }


    private fun compareDayTo(calendar1: Calendar, calendar2: Calendar): Boolean {
        if (calendar1[Calendar.DAY_OF_MONTH] == calendar2[Calendar.DAY_OF_MONTH] && calendar1[Calendar.MONTH] == calendar2[Calendar.MONTH])
            return true
        return false
    }
}