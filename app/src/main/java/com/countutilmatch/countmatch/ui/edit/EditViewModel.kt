package com.countutilmatch.countmatch.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase
import com.countutilmatch.countmatch.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditViewModel@Inject constructor(val dataBase: EventDataBase): BaseViewModel() {

    val event: LiveData<Event> get() = eventMutable
    private val eventMutable = MutableLiveData<Event>()

    fun init(event_id:Long){
        viewModelScope.launch {
            eventMutable.value = dataBase.eventDatabaseDao.get(event_id)
        }
    }

    fun delete(it: Long) {
        viewModelScope.launch {
            dataBase.eventDatabaseDao.deleteItem(it)
        }
    }
}