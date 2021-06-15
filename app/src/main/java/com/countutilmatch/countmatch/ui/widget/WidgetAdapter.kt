package com.countutilmatch.countmatch.ui.widget

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.database.EventDataBase

class WidgetAdapter(private val context: Context, val intent: Intent): RemoteViewsService.RemoteViewsFactory {


    var dataBase: EventDataBase = EventDataBase.getInstance(context)
    var eventList: List<Event> = ArrayList<Event>()

    override fun onCreate() {
    }

    override fun onDataSetChanged() {
        eventList = dataBase.eventDatabaseDao.getAllEventsNotLive()
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int {
        return eventList.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_item)
        rv.setTextViewText(R.id.timerWidget, eventList[position].endDate + " " + eventList[position].endTime)
        rv.setTextViewText(R.id.titleWidget, eventList[position].title)
        if (eventList[position].isBought){
            rv.setTextViewText(R.id.ticketBoughtOrNotWidget, context.getString(R.string.ticker_bought))
            rv.setImageViewResource(R.id.imageTicketWidget, R.drawable.ic_bought_ticket)
        }else{
            rv.setTextViewText(R.id.ticketBoughtOrNotWidget, context.getString(R.string.ticker_not_bought))
            rv.setImageViewResource(R.id.imageTicketWidget, R.drawable.ic_not_bought_ticket)
        }

        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}