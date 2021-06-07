package com.countutilmatch.countmatch.di

import com.countutilmatch.countmatch.Application.Companion.context
import com.countutilmatch.countmatch.database.EventDataBase
import com.countutilmatch.countmatch.database.EventDatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideDB(): EventDataBase {
        return EventDataBase.getInstance(context)
    }

}
