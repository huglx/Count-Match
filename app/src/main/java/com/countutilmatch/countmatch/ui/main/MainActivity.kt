package com.countutilmatch.countmatch.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityMainBinding
import com.countutilmatch.countmatch.databinding.TicketItemBinding
import com.countutilmatch.countmatch.ui.adding.AddingActivity
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.edit.EditActivity
import com.countutilmatch.countmatch.ui.settings.SettingsActivity
import com.countutilmatch.countmatch.ui.splash.GreetingActivity
import com.countutilmatch.countmatch.utils.*
import com.task.ui.base.listeners.RecyclerItemListener
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var bindings: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var eventAdapter: EventAdapter
    private lateinit var audioManager: AudioManager

    override fun initViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    override fun initBindings() {
        bindings = ActivityMainBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(viewModel.events,:: handleData)
    }

    private fun handleData(events: List<Event>) {
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        eventAdapter = EventAdapter(
            events, EventListener(clickListener = {
                if (sharedPref.getBoolean(SOUNDS, true)){
                    audioManager.startSound()
                }
                startActivity(Intent(this, EditActivity::class.java)
                    .putExtra("EVENT_ID", it))
                finish()
            },clickDeleteListener = {
                if (sharedPref.getBoolean(SOUNDS, true)){
                    audioManager.startDeleteSound()
                }
                viewModel.delete(it)
                eventAdapter.notifyDataSetChanged()
                bindings.deleteAll.visibility = View.GONE
            }), longCLickListener
        )
        bindings.listItem.adapter = eventAdapter

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean(IS_GREETING_PASSED, false)){
            startActivity(Intent(this, GreetingActivity::class.java))
        }

        when(sharedPref.getString(LANGUAGE, "ru")){
            "ru" -> setAppLocale(this,"ru")
            "en" -> setAppLocale(this, "en")
            "de" -> setAppLocale(this, "de")
        }

        super.onCreate(savedInstanceState)
        bindings.lifecycleOwner = this
        audioManager = AudioManager(this)

        bindings.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddingActivity::class.java))
        }
        bindings.deleteAll.setOnClickListener {
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startDeleteSound()
            }
            viewModel.deleteAll()
            eventAdapter.notifyDataSetChanged()
            bindings.deleteAll.visibility = View.GONE
        }
        bindings.settings.setOnClickListener {
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startSound()
            }
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }

        setUp()
        viewModel.init(this)
    }
    private fun setUp() {
        val manager = LinearLayoutManager(this)
        bindings.listItem.layoutManager = manager
    }

    override fun onBackPressed() {
        moveTaskToBack(false);
    }

    private val longCLickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onLongCLickSelected(binding: TicketItemBinding) {
            if (binding.delete.isVisible) {
                binding.delete.visibility = View.GONE
                bindings.deleteAll.visibility = View.GONE
                bindings.floatingActionButton.visibility = View.VISIBLE
            } else {
                    binding.delete.visibility = View.VISIBLE
                    bindings.deleteAll.visibility = View.VISIBLE
                    bindings.floatingActionButton.visibility = View.GONE
                }
            }
        }

}