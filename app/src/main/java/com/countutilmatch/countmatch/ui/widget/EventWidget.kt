package com.countutilmatch.countmatch.ui.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import com.countutilmatch.countmatch.R
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
    lateinit var event: LiveData<List<Event>>
    private val eventMutable = MutableLiveData<Event>()

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        dataBase = EventDataBase.getInstance(context)
        event = dataBase.eventDatabaseDao.getAllEvents()
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val man = AppWidgetManager.getInstance(context)
        val ids = man.getAppWidgetIds(
            ComponentName(context, EventWidget::class.java))

        event.observe(ProcessLifecycleOwner.get(), {
            for (appWidgetId in ids) {
                if (it != null) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, it)
                }else updateAppWidget(context, appWidgetManager, appWidgetId, null)
            }
        })
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    eventList: List<Event>?
) {

    val views = RemoteViews(context.packageName, R.layout.event_widget)
    val intent = Intent(context, WidgetRemoteViewsService::class.java)
    views.setRemoteAdapter(R.id.listView, intent)

    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.listView)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}