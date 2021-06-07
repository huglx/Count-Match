package com.countutilmatch.countmatch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event_table")
data class Event (

   /* @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time")
    var endTimeMilli: Date,*/

    @ColumnInfo(name = "title")
    var title: String,
/*
    @ColumnInfo(name = "isBought")
    var isBought: Boolean*/

    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0L
    )
