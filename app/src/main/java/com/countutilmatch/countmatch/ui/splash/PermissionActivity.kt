package com.countutilmatch.countmatch.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityPermissionBinding
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.utils.AudioManager
import com.countutilmatch.countmatch.utils.LANGUAGE
import com.countutilmatch.countmatch.utils.PREF
import com.countutilmatch.countmatch.utils.SOUNDS

class PermissionActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityPermissionBinding
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        audioManager = AudioManager(this)

        bindings.next.setOnClickListener {
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startSound()
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    fun initBindings() {
        bindings = ActivityPermissionBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

}