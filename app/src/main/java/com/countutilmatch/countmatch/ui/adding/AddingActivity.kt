package com.countutilmatch.countmatch.ui.adding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.ActivityAddingBinding
import com.countutilmatch.countmatch.ui.base.BaseActivity
import com.countutilmatch.countmatch.ui.main.MainActivity
import com.countutilmatch.countmatch.utils.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class AddingActivity : BaseActivity() {

    private lateinit var bindings: ActivityAddingBinding
    @Inject
    lateinit var viewModel: AddingViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var audioManager: AudioManager

    override fun initViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    override fun initBindings() {
        bindings = ActivityAddingBinding.inflate(layoutInflater)
        val view = bindings.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(viewModel.event,::handleData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences(PREF , Context.MODE_PRIVATE)
        audioManager = AudioManager(this)

        bindings.addEvent.setOnClickListener {
            if (checkData()) {
                viewModel.insert(
                    bindings.title.editText?.text.toString().trim(),
                    bindings.date.editText?.text.toString().trim(),
                    bindings.dateTime.editText?.text.toString().trim(),
                    bindings.ticketBoughtOrNot.isChecked
                )
                if (sharedPref.getBoolean(SOUNDS, true)){
                    audioManager.startSound()
                }
                startActivity(Intent(this, ResultActivity::class.java))
            }
        }
    }

    private fun checkData(): Boolean{
       /* var formatterTime = DateTimeFormatter.ofPattern("HH-mm-ss")
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")*/
        if (bindings.title.editText?.text.toString().trim().isEmpty() || bindings.date.editText?.text.toString().trim().isEmpty()
            || bindings.dateTime.editText?.text.toString().trim().isEmpty()
            || bindings.title.editText?.text.toString().length > 27){
            Toast.makeText(this, getString(R.string.fill_data), Toast.LENGTH_SHORT).show()
            return false
        }else if( !(isValidDate(bindings.date.editText?.text.toString().trim(),"dd-MM-yyyy")) ){
            Toast.makeText(this, getString(R.string.invalid_date), Toast.LENGTH_SHORT).show()
            return false
        }else if( !(isValidDate(bindings.dateTime.editText?.text.toString().trim(),"HH-mm-ss")) ){
            Toast.makeText(this, getString(R.string.invalid_date_time), Toast.LENGTH_SHORT).show()
            return false
        }else if( !(isDateInFuture(bindings.date.editText?.text.toString().trim(), bindings.dateTime.editText?.text.toString().trim())) ){
            Toast.makeText(this, getString(R.string.date_inPast), Toast.LENGTH_SHORT).show()
            return false
        }
            return true
    }

    private fun isValidDate(str:String, format:String):Boolean{
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat(format)
            date = sdf.parse(str)
            if (str.equals(sdf.format(date))) {
                date = null
                return true
            }else{
                return false
            }
        }catch (e:Exception){
            return false
        }

    }

     private fun isDateInFuture(date:String, dateTime:String):Boolean{

         val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH-mm-ss")//"dd-MM-yyyy,HH-mm-ss"
         // val date = item.endDate+","+item.endTime
         val date = (date+","+dateTime)
         val text = date.format(formatter)
         val parsedDate = LocalDateTime.parse(text, formatter)

         val calendar = Calendar.getInstance()
         calendar.set(Calendar.SECOND, parsedDate.second)
         calendar.set(Calendar.HOUR_OF_DAY, parsedDate.hour)
         calendar.set(Calendar.MONTH,parsedDate.monthValue-1)
         calendar.set(Calendar.MINUTE,parsedDate.minute)
         calendar.set(Calendar.DAY_OF_MONTH, parsedDate.dayOfMonth)
         calendar.set(Calendar.YEAR, parsedDate.year)

         return calendar.timeInMillis >= System.currentTimeMillis()

    }


    private fun handleData(event: Event) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}