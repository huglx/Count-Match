package com.countutilmatch.countmatch.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityGreetingBinding
import com.countutilmatch.countmatch.utils.*

class GreetingActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityGreetingBinding
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        audioManager = AudioManager(this)

        initBindings()

        bindings.next.setOnClickListener {
            val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startSound()
            }
            startActivity(Intent(this, PermissionActivity::class.java))
            editor.putBoolean(IS_GREETING_PASSED, true).apply()
        }
    }

    fun initBindings() {
        bindings = ActivityGreetingBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }
}