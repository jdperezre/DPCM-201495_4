package com.culturapp.culturapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.culturapp.culturapp.R
import com.culturapp.culturapp.models.Event

class EventListNotificationAdapter(private var activity: FragmentActivity, private var items: List<Event>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtTitle: TextView? = null
        var txtDate: TextView? = null
        var txtDateAlarm: TextView? = null
        var txtHour: TextView? = null
        var txtLocation: TextView? = null
        var imgMenu : ImageView? = null

        init {
            this.txtTitle = row?.findViewById<TextView>(R.id.txtTitle)
            this.txtDate = row?.findViewById<TextView>(R.id.txtDate)
            this.txtDateAlarm = row?.findViewById<TextView>(R.id.txtAlarmDate)
            this.txtHour = row?.findViewById<TextView>(R.id.txtAlarmHour)
            this.txtLocation = row?.findViewById<TextView>(R.id.txtLocation)
            this.imgMenu = row?.findViewById<ImageView>(R.id.imgMenu)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.event_alarm_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var eventDto = items[position]
        viewHolder.txtTitle?.text = eventDto.titulo
        viewHolder.txtDate?.text = eventDto.fechaInicio
        viewHolder.txtDateAlarm?.text = eventDto.fechaInicio
        viewHolder.txtHour?.text = eventDto.hora
        viewHolder.txtLocation?.text = eventDto.direccion

        viewHolder.imgMenu?.setOnClickListener {
            showPopupMenuAlarm(it, R.menu.contextual_alarm_menu, eventDto.id)
        }

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

    private fun showPopupMenuAlarm(view: View, menu: Int, id: Int) {
        val popupMenu = PopupMenu(activity, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_alarm -> Toast.makeText(activity, "Alarma editada", Toast.LENGTH_SHORT).show()
                R.id.delete_alarm -> Toast.makeText(activity, "Alarma eliminada", Toast.LENGTH_SHORT).show()
            }
            true
        }
        popupMenu.show()
    }
}