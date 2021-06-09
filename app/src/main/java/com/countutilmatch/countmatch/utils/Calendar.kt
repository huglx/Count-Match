package com.countutilmatch.countmatch.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getCalendar(date:String, time:String):Calendar{
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH-mm-ss")//"dd-MM-yyyy,HH-mm-ss"
    // val date = item.endDate+","+item.endTime
    val date = (date+","+time)
    val text = date.format(formatter)
    val parsedDate = LocalDateTime.parse(text, formatter)

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.SECOND, parsedDate.second)
    calendar.set(Calendar.HOUR_OF_DAY, parsedDate.hour)
    calendar.set(Calendar.MONTH,parsedDate.monthValue-1)
    calendar.set(Calendar.MINUTE,parsedDate.minute)
    calendar.set(Calendar.DAY_OF_MONTH, parsedDate.dayOfMonth)
    calendar.set(Calendar.YEAR, parsedDate.year)

    return calendar
}
