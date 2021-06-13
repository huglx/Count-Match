package com.countutilmatch.countmatch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event_table")
data class Event (

    @ColumnInfo(name = "add_time_milli")
    val addTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "end_date")
    var endDate: String,

    @ColumnInfo(name = "end_time")
    var endTime: String,

    @ColumnInfo(name = "isBought")
    var isBought: Boolean,

    @ColumnInfo(name = "IsEnded")
    var IsEnded: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0L
    )
