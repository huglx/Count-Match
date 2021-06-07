package com.countutilmatch.countmatch.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity() {
    protected abstract fun initViewModel()
    protected abstract fun initBindings()
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initBindings()
        initViewModel()
        observeViewModel()
    }
}