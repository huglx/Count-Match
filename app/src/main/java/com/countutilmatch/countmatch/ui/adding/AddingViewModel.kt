package com.countutilmatch.countmatch.ui.adding

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
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

class AddingViewModel @Inject constructor(val dataBase: EventDataBase): BaseViewModel() {

    val event: LiveData<Event> get() = eventMutable
    private val eventMutable = MutableLiveData<Event>()
    fun init(){

    }

    fun insert(title:String, date:String, dateTime:String, isBought:Boolean) {
        val mEvent = Event(title = title, endDate = date, endTime = dateTime, isBought = isBought)
        viewModelScope.launch {
            dataBase.eventDatabaseDao.insert(mEvent)
        }
    }
}