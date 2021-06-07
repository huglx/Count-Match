package com.countutilmatch.countmatch.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityMainBinding
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.splash.GreetingActivity
import com.countutilmatch.countmatch.ui.splash.PermissionActivity
import com.countutilmatch.countmatch.utils.IS_GREETING_PASSED
import com.countutilmatch.countmatch.utils.ViewModelFactory
import com.countutilmatch.countmatch.utils.observe
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var bindings: ActivityMainBinding
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
        eventAdapter = EventAdapter(events)
        bindings.listItem.adapter = eventAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings.lifecycleOwner = this

        val sharedPref = this.getSharedPreferences(IS_GREETING_PASSED , Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean(IS_GREETING_PASSED, false)){
            startActivity(Intent(this, GreetingActivity::class.java))
        }

        setUp()
        viewModel.init()
    }

    private fun setUp() {

        val manager = LinearLayoutManager(this)
        bindings.listItem.layoutManager = manager
    }
}