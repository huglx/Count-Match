package com.countutilmatch.countmatch.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.ui.main.MainViewModel

@BindingAdapter("iconTicket")
fun ImageView.setIcon(item: Event?) {
    item?.let {
        if (it.isBought){
            setImageResource(R.drawable.ic_bought_ticket)
        }else{
            setImageResource(R.drawable.ic_not_bought_ticket)
        }
    }
}

@BindingAdapter("isBought")
fun TextView.isBought(item: Event?) {
    item?.let {
        if (it.isBought){
            text = resources.getString(R.string.ticker_bought)
        }else{
            text = resources.getString(R.string.ticker_not_bought)

        }
    }
}

/*@BindingAdapter("onLongClick")
fun setOnLongClickListener(
    view: View,
    func : () -> Unit
) {
    view.setOnLongClickListener {
        func()
        Log.i("asfasf", "onLongPress: ")
        return@setOnLongClickListener true
    }
}*/


@BindingAdapter("onLongClick")
fun setOnLongClickListener(view: View, func : () -> Unit ) {
    view.setOnLongClickListener {
        func()
        Log.i("asfasf", "setOnLongClickListener")
        true
    }
}
