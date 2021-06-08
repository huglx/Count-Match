package com.countutilmatch.countmatch.ui.main

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.TicketItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class EventAdapter(private val events: List<Event>, val clickListener: EventListener) :
        RecyclerView.Adapter<EventViewHolder>() {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding = TicketItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
       return events.size
    }
}

class EventViewHolder constructor(private val binding: TicketItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event, clickListener: EventListener) {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH-mm-ss")//"dd-MM-yyyy,HH-mm-ss"
           // val date = item.endDate+","+item.endTime
            val date = (item.endDate+","+item.endTime)
            val text = date.format(formatter)
            val parsedDate = LocalDateTime.parse(text, formatter)

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.SECOND, parsedDate.second)
            calendar.set(Calendar.HOUR_OF_DAY, parsedDate.hour)
            calendar.set(Calendar.MONTH,parsedDate.monthValue-1)
            calendar.set(Calendar.MINUTE,parsedDate.minute)
            calendar.set(Calendar.DAY_OF_MONTH, parsedDate.dayOfMonth)
            calendar.set(Calendar.YEAR, parsedDate.year)

            val timer = object: CountDownTimer(calendar.timeInMillis - System.currentTimeMillis(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                    val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished).toInt() % 24
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt() % 60
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() % 60
                    binding.timer.text = itemView.context.resources.getString(R.string.timer, day, hours, minutes, seconds)
                }

                override fun onFinish() {

                }
            }.start()

            binding.event = item
            binding.title.text = item.title
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

class EventListener(val clickListener: (eventId: Long) -> Unit, val longClickListener: () -> Unit) {
    fun onClick(event: Event) = clickListener(event.eventId)
    fun onLongPress() = longClickListener()
}
