package com.countutilmatch.countmatch.ui.adding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityGreetingBinding
import com.countutilmatch.countmatch.databinding.ActivityResultBinding
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.utils.AudioManager
import com.countutilmatch.countmatch.utils.LANGUAGE
import com.countutilmatch.countmatch.utils.PREF

class ResultActivity : AppCompatActivity() {
    lateinit var bindings: ActivityResultBinding
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        audioManager = AudioManager(this)

        bindings.addMore.setOnClickListener {
            if (sharedPref.getBoolean(LANGUAGE, true)) audioManager.startSound()
            startActivity(Intent(this, AddingActivity::class.java))
        }
        bindings.goMain.setOnClickListener {
            if (sharedPref.getBoolean(LANGUAGE, true)) audioManager.startSound()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun initBindings() {
        bindings = ActivityResultBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun onBackPressed() {
    }
}