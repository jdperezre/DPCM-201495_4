package com.culturapp.culturapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.culturapp.culturapp.R
import com.culturapp.culturapp.models.Event

class EventListAdapter(private var activity: Activity, private var items: List<Event>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtTitle: TextView? = null
        var txtDate: TextView? = null
        var txtLocation: TextView? = null

        init {
            this.txtTitle = row?.findViewById<TextView>(R.id.txtTitle)
            this.txtDate = row?.findViewById<TextView>(R.id.txtDate)
            this.txtLocation = row?.findViewById<TextView>(R.id.txtLocation)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.event_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var eventDto = items[position]
        viewHolder.txtTitle?.text = eventDto.titulo
        viewHolder.txtDate?.text = eventDto.fechaInicio
        viewHolder.txtLocation?.text = eventDto.direccion

        return view as View
    }

    override fun getItem(i: Int): Event {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}