package com.countutilmatch.countmatch.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase
import com.countutilmatch.countmatch.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(val dataBase: EventDataBase): BaseViewModel() {

    val event: LiveData<Event> get() = eventMutable
    private val eventMutable = MutableLiveData<Event>()

    fun init(){

    }

    fun deleteAll() {
        viewModelScope.launch {
            dataBase.eventDatabaseDao.clear()
        }
    }
}