package com.countutilmatch.countmatch

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
class EventWidget : AppWidgetProvider() {
    lateinit var dataBase: EventDataBase
    val job = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    lateinit var event: LiveData<Event>
    private val eventMutable = MutableLiveData<Event>()

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        dataBase = EventDataBase.getInstance(context)
        event = dataBase.eventDatabaseDao.getSoonest()
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val man = AppWidgetManager.getInstance(context)
        val ids = man.getAppWidgetIds(
            ComponentName(context, EventWidget::class.java))

        event.observe(ProcessLifecycleOwner.get(), {
            for (appWidgetId in ids) {
                Log.i("sfasad", it.title.toString())
                updateAppWidget(context, appWidgetManager, appWidgetId, it.title)
            }
        })
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    title: String
) {
    /*al job = SupervisorJob()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)*/
    //val dataBase = EventDataBase.getInstance(context)
    /*coroutineScope.launch {
        Log.i("sfasad", dataBase.eventDatabaseDao.getAllEvents().value?.get(0)?.title.toString())

    }*/
    var widgetText = "Sfa"
    if (title != null) {
        widgetText = title
    }
  //  Log.i("sfasad", event.value?.get(0)?.title.toString())
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.event_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}