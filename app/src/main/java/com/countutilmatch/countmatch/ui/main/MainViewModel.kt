package com.countutilmatch.countmatch.ui.main

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase
import com.countutilmatch.countmatch.ui.base.BaseViewModel
import com.countutilmatch.countmatch.utils.ForegroundService
import com.countutilmatch.countmatch.utils.getCalendar
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(val dataBase: EventDataBase): BaseViewModel() {
    val events = dataBase.eventDatabaseDao.getAllEvents()
    private val eventMutable = MutableLiveData<List<Event>>()
   // val mEvent = Event("TEST123")

    fun init(context: Context) {

    }

    fun func(){

    }

    fun delete(it: Long) {
        viewModelScope.launch {
            dataBase.eventDatabaseDao.deleteItem(it)

        }
    }

    fun update(it: Event) {
        viewModelScope.launch {
            dataBase.eventDatabaseDao.update(it)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            dataBase.eventDatabaseDao.clear()
        }
    }
}