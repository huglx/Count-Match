package com.countutilmatch.countmatch.ui.widget

import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService

class WidgetRemoteViewsService: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return WidgetAdapter(this.applicationContext, intent)
    }
}