package com.countutilmatch.countmatch.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityPermissionBinding
import com.countutilmatch.countmatch.ui.main.MainActivity

class PermissionActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()

        bindings.next.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    fun initBindings() {
        bindings = ActivityPermissionBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

}