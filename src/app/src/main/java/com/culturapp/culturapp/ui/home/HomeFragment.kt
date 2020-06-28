package com.culturapp.culturapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListHomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        var adapter = EventListHomeAdapter(this.requireActivity(), generateData())

        events_listView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    fun generateData(): ArrayList<EventDto> {
        var result = ArrayList<EventDto>()

        for (i in 0..9) {
            var dto: EventDto = EventDto((i + 1).toString() + " Titulo Evento", "Mayo " +(i + 1).toString() + " de 2020", "Centro de espectáculos salón " + (i + 1).toString())
            result.add(dto)
        }

        return result
    }
}

class EventDto(title: String, date: String, location: String
) {
    var title = title
    var date =  date
    var location =  location
}
