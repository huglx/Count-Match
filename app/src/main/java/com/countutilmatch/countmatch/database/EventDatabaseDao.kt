package com.countutilmatch.countmatch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventDatabaseDao {
    @Insert
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("SELECT * from event_table WHERE eventId = :key")
    suspend fun get(key: Long): Event?

    @Query("DELETE FROM event_table")
    suspend fun clear()

    @Query("DELETE FROM event_table WHERE eventId = :key")
    suspend fun deleteItem(key: Long)

    @Query("SELECT * FROM event_table")
    fun getAllEvents(): LiveData<List<Event>>
}
