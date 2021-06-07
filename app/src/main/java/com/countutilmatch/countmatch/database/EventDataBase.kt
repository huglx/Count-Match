package com.countutilmatch.countmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDataBase: RoomDatabase() {
    abstract val eventDatabaseDao: EventDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: EventDataBase? = null

        fun getInstance(context: Context): EventDataBase {

            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventDataBase::class.java,
                        "event_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}