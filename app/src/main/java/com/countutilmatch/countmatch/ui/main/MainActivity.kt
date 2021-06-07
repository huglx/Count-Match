package com.countutilmatch.countmatch.ui.main

import android.os.Bundle
import com.countutilmatch.countmatch.databinding.ActivityMainBinding
import com.countutilmatch.countmatch.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var bindings: ActivityMainBinding

    override fun initViewModel() {
        TODO("Not yet implemented")
    }

    override fun initBindings() {
        bindings = ActivityMainBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}