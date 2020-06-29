package com.culturapp.culturapp.ui.notifications

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListNotificationAdapter

import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onResume() {
        //nameEditText.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        //nameEditText.SetHint(R.string.hint_custom_edittext);
        //nameEditText.SetIcon(R.drawable.ic_home_black_24dp)
        super.onResume()

        var adapter = EventListNotificationAdapter(this.requireActivity(), generateData())

        events_notification_listView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }


    fun generateData(): ArrayList<EventDto> {
        var result = ArrayList<EventDto>()

        for (i in 0..10) {
            var dto: EventDto = EventDto(
                    (i + 1).toString() + " Titulo Evento",
                    "Mayo " + (i + 1).toString() + ", 2020",
                    "Centro de espectáculos salón " + (i + 1).toString(),
                    "7:00 pm."
                )
            result.add(dto)
        }

        return result
    }
}

class EventDto(title: String, date: String,  location: String, hour: String
) {
    var title = title
    var date =  date
    var hour = hour
    var location =  location
}
