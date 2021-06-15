package com.countutilmatch.countmatch.ui.edit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityEditBinding
import com.countutilmatch.countmatch.databinding.ActivityMainBinding
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.main.EventAdapter
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.ui.main.MainViewModel
import com.countutilmatch.countmatch.ui.splash.GreetingActivity
import com.countutilmatch.countmatch.utils.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EditActivity : BaseActivity() {

    private lateinit var bindings: ActivityEditBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: EditViewModel
    private lateinit var audioManager: AudioManager

    override fun initViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)

    }

    override fun initBindings() {
        bindings = ActivityEditBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(viewModel.event,:: handleData)
    }

    private fun handleData(event: Event) {
        bindings.title.text = event.title
        if (event.IsEnded) {
            bindings.countdown.text = getString(R.string.event_ended)
            bindings.day.visibility = View.GONE
            bindings.hours.visibility = View.GONE
            bindings.minutes.visibility = View.GONE
            bindings.secs.visibility = View.GONE
            bindings.dayText.visibility = View.GONE
            bindings.hoursText.visibility = View.GONE
            bindings.minutesText.visibility = View.GONE
            bindings.secsText.visibility = View.GONE

        }else {
            val timer = object : CountDownTimer(
                getCalendar(
                    event.endDate,
                    event.endTime
                ).timeInMillis - System.currentTimeMillis(), 1000
            ) {
                override fun onTick(millisUntilFinished: Long) {
                    val day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                    val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished).toInt() % 24
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt() % 60
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() % 60

                    bindings.day.text = day.toString()
                    bindings.hours.text = hours.toString()
                    bindings.minutes.text = minutes.toString()
                    bindings.secs.text = seconds.toString()
                }

                override fun onFinish() {
                    bindings.countdown.text = getString(R.string.event_ended)
                    bindings.day.visibility = View.GONE
                    bindings.hours.visibility = View.GONE
                    bindings.minutes.visibility = View.GONE
                    bindings.secs.visibility = View.GONE
                    bindings.dayText.visibility = View.GONE
                    bindings.hoursText.visibility = View.GONE
                    bindings.minutesText.visibility = View.GONE
                    bindings.secsText.visibility = View.GONE
                }
            }.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        val event_id = intent.extras?.get("EVENT_ID")
        viewModel.init(event_id as Long)

        audioManager = AudioManager(this)

        bindings.delete.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog)
                .setMessage(resources.getString(R.string.alert))
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    if (sharedPref.getBoolean(SOUNDS, true)){
                        audioManager.startDeleteSound()
                    }
                    viewModel.delete(event_id)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                .show()
        }

        bindings.save.setOnClickListener {
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startSound()
            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))

    }
}