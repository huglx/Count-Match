package com.countutilmatch.countmatch.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
    }


    fun initBindings() {
        bindings = ActivityPermissionBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

}