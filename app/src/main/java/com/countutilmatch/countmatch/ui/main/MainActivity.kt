package com.countutilmatch.countmatch.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityMainBinding
import com.countutilmatch.countmatch.databinding.TicketItemBinding
import com.countutilmatch.countmatch.ui.adding.AddingActivity
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.edit.EditActivity
import com.countutilmatch.countmatch.ui.splash.GreetingActivity
import com.countutilmatch.countmatch.ui.splash.PermissionActivity
import com.countutilmatch.countmatch.utils.IS_GREETING_PASSED
import com.countutilmatch.countmatch.utils.ViewModelFactory
import com.countutilmatch.countmatch.utils.observe
import com.countutilmatch.countmatch.utils.sendNotification
import com.google.android.material.internal.ContextUtils.getActivity
import com.task.ui.base.listeners.RecyclerItemListener
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var bindings: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun initViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    override fun initBindings() {
        bindings = ActivityMainBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(viewModel.event,:: handleData)
    }

    private fun handleData(events: List<Event>) {
        eventAdapter = EventAdapter(events, EventListener(clickListener = {
            startActivity(Intent(this, EditActivity::class.java)
                .putExtra("EVENT_ID", it))
            finish()
        },clickDeleteListener = {
            viewModel.delete(it)
            eventAdapter.notifyDataSetChanged()
        })
        )

        bindings.listItem.adapter = eventAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings.lifecycleOwner = this

        val sharedPref = this.getSharedPreferences(IS_GREETING_PASSED , Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean(IS_GREETING_PASSED, false)){
            startActivity(Intent(this, GreetingActivity::class.java))
        }
        bindings.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddingActivity::class.java))
        }
        setUp()
        viewModel.init()

        createChannel(
           "1",
            "name"
        )
    }

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                // TODO: Step 2.4 change importance
                NotificationManager.IMPORTANCE_HIGH
            )// TODO: Step 2.6 disable badges for this channel
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "desc"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        // TODO: Step 1.6 END create a channel
    }

    private fun setUp() {
        val manager = LinearLayoutManager(this)
        bindings.listItem.layoutManager = manager

        val notificationManager = ContextCompat.getSystemService(
            this,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            "test",
            this
        )
    }

    override fun onBackPressed() {
        moveTaskToBack(false);
    }
}