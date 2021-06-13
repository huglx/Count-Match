package com.countutilmatch.countmatch.ui.main

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countutilmatch.countmatch.R
import com.countutilmatch.countmatch.database.Event
import com.countutilmatch.countmatch.databinding.TicketItemBinding
import com.countutilmatch.countmatch.utils.getCalendar
import com.task.ui.base.listeners.RecyclerItemListener
import java.util.*
import java.util.concurrent.TimeUnit


class EventAdapter(
    private val events: List<Event>,
    val clickListener: EventListener,
    val longCLickListener: RecyclerItemListener
) :
        RecyclerView.Adapter<EventViewHolder>() {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], clickListener, longCLickListener)
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
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Event, clickListener: EventListener, longCLickListener: RecyclerItemListener) {
            val timer = object: CountDownTimer(getCalendar(item.endDate, item.endTime).timeInMillis - System.currentTimeMillis(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                    val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished).toInt() % 24
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt() % 60
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() % 60
                    binding.timer.text = itemView.context.resources.getString(R.string.timer, day, hours, minutes, seconds)
                }

                override fun onFinish() {
                    binding.timer.text = itemView.context.resources.getString(R.string.event_ended)
                }
            }.start()

            binding.event = item
            binding.title.text = item.title
            binding.listItem.setOnLongClickListener {
                longCLickListener.onLongCLickSelected(binding)
                true
            }
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
}

class EventListener(val clickListener: (eventId: Long) -> Unit, val clickDeleteListener: (eventId: Long) -> Unit) {
    fun onClick(event: Event) = clickListener(event.eventId)
    fun onClickDelete(event: Event) = clickDeleteListener(event.eventId)
}
