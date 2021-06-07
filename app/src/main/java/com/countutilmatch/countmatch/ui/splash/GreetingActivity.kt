package com.countutilmatch.countmatch.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityGreetingBinding
import com.countutilmatch.countmatch.utils.IS_GREETING_PASSED

class GreetingActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityGreetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()


        bindings.next.setOnClickListener {
            val sharedPref = this.getSharedPreferences(IS_GREETING_PASSED , Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
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