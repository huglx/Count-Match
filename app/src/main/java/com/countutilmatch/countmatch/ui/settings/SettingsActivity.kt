package com.countutilmatch.countmatch.ui.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityAddingBinding
import com.countutilmatch.countmatch.databinding.ActivitySettingsBinding
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.ui.main.MainViewModel_Factory
import com.countutilmatch.countmatch.utils.*
import javax.inject.Inject

class SettingsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: SettingsViewModel
    lateinit var bindings: ActivitySettingsBinding
    private lateinit var audioManager: AudioManager


    override fun initViewModel() {
        viewModelFactory.create(viewModel::class.java)
    }

    override fun initBindings() {
        bindings = ActivitySettingsBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(viewModel.event,::handleData)
    }

    private fun handleData(event: Event) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        audioManager = AudioManager(this)
        bindings.sound.isChecked = sharedPref.getBoolean(SOUNDS, true)

        when(sharedPref.getString(LANGUAGE, "ru")){
            "ru" -> bindings.ru.isChecked = true
            "en" -> bindings.en.isChecked = true
            "de" -> bindings.de.isChecked = true
        }

        bindings.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId){
                R.id.ru -> setLanguage("ru")
                R.id.en -> setLanguage("en")
                R.id.de -> setLanguage("de")
            }
        }

        bindings.sound.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) sharedPref.edit().putBoolean(SOUNDS, true).apply()
            else sharedPref.edit().putBoolean(SOUNDS, false).apply()
        }

        bindings.goMain.setOnClickListener {
            if (sharedPref.getBoolean(SOUNDS, true)){
                audioManager.startSound()
            }
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        bindings.deleteAll.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun setLanguage(language: String) {
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        sharedPref.edit().putString(LANGUAGE, language).apply()
        setAppLocale(this,language)
        finish()
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}