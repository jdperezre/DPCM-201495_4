package com.culturapp.culturapp.adapters

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import com.culturapp.culturapp.R
import com.culturapp.culturapp.models.Event
import com.culturapp.culturapp.ui.events.EventsActivity
import java.util.*

class EventListAdapter(private var activity: Activity, private var items: List<Event>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtTitle: TextView? = null
        var txtDate: TextView? = null
        var txtLocation: TextView? = null
        var imgMenu : ImageView? = null

        init {
            this.txtTitle = row?.findViewById<TextView>(R.id.txtTitle)
            this.txtDate = row?.findViewById<TextView>(R.id.txtDate)
            this.txtLocation = row?.findViewById<TextView>(R.id.txtLocation)
            this.imgMenu = row?.findViewById<ImageView>(R.id.imgMenu)
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

        viewHolder.imgMenu?.setOnClickListener {
            if(activity is EventsActivity)
                showPopupMenu(it, R.menu.contextual_events_menu, eventDto.id)
            else
                showPopupMenu(it, R.menu.contextual_menu, eventDto.id)
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

    private fun showPopupMenu(view: View, menu: Int, id: Int) {
        val popupMenu = PopupMenu(activity, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_alarm -> showCalendarAlarm()
                R.id.delete_favorite -> Toast.makeText(activity, "Favorito eliminado", Toast.LENGTH_SHORT).show()
                R.id.edit_event -> Toast.makeText(activity, "Evento editado", Toast.LENGTH_SHORT).show()
                R.id.delete_event -> Toast.makeText(activity, "Evento eliminado", Toast.LENGTH_SHORT).show()
            }
            true
        }
        popupMenu.show()
    }



    lateinit var context: Context
    lateinit var alarmManager: AlarmManager


    private fun showCalendarAlarm() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        this.context = activity;

        val dpdDate = DatePickerDialog(this.context,  DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->
            val dpdHour = TimePickerDialog(this.context,  TimePickerDialog.OnTimeSetListener{ view, mHour, mMinute->
                val calendar: Calendar = GregorianCalendar(
                    mYear,
                    mMonth,
                    mDay,
                    mHour,
                    mMinute
                )

                createAlarm(calendar.timeInMillis)

            }, hour, minute, false)
            dpdHour.show()
        }, year, month, day)
        dpdDate.show()

    }


    public fun createAlarm(milliseconds: Long){
        //alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Create : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, pendingIntent)
    }

    public fun updateAlarm(milliseconds: Long){
        //alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Update : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, pendingIntent)
    }

    public fun cancelAlarm(){
        //alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Cancel : " + Date().toString())
        alarmManager.cancel(pendingIntent)
    }
}

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MainActivity", " Receiver : " + Date().toString())
    }
}