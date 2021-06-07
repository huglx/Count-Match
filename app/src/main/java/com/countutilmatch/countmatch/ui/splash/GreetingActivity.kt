package com.countutilmatch.countmatch.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityGreetingBinding

class GreetingActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityGreetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()

        bindings.next.setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java)) }
    }

    fun initBindings() {
        bindings = ActivityGreetingBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }
}