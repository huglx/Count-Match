package com.countutilmatch.countmatch.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase
import com.countutilmatch.countmatch.database.EventDatabaseDao
import com.countutilmatch.countmatch.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(val dataBase: EventDataBase): BaseViewModel() {
    val event = dataBase.eventDatabaseDao.getAllEvents()
    private val eventMutable = MutableLiveData<List<Event>>()

    val longPressed: LiveData<Boolean> get() = longPressedMutable
    private val longPressedMutable = MutableLiveData<Boolean>()
   // val mEvent = Event("TEST123")

    fun init(){
        longPressedMutable.value = false
        viewModelScope.launch {
            //dataBase.eventDatabaseDao.insert(mEvent)
        }
    }
}