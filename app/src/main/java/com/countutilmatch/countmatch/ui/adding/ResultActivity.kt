package com.countutilmatch.countmatch.ui.adding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityGreetingBinding
import com.countutilmatch.countmatch.databinding.ActivityResultBinding
import com.countutilmatch.countmatch.ui.main.MainActivity

class ResultActivity : AppCompatActivity() {
    lateinit var bindings: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        bindings.addMore.setOnClickListener {
            startActivity(Intent(this, AddingActivity::class.java))
        }
        bindings.goMain.setOnClickListener {
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