package com.culturapp.culturapp.ui.home

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListHomeAdapter
import com.culturapp.culturapp.models.Event
import kotlinx.android.synthetic.main.detail_event.*
import java.util.*
import kotlin.collections.ArrayList


class DetailFragment(
    val eventDto: Event
): Fragment() {

    lateinit var progressProgressDialog: ProgressDialog
    var dataList = ArrayList<Event>()
    lateinit var adapter: EventListHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_event, container, false)
    }

    override fun onResume() {
        super.onResume()

        loadInfo()
    }

    private fun loadInfo() {
        txtTitle.text = eventDto.titulo
        txtAlarmDate.text = eventDto.fechaInicio
        txtAlarmHour.text = eventDto.hora
        txtLocation.text = eventDto.direccion
        textDescription.text = eventDto.descripcion

        btnCall.setOnClickListener {
            onCall(eventDto.telefono)
        }


        btnNotification.setOnClickListener {
            showCalendarAlarm()
        }


    }

    fun onCall(tel: String) {
        val permissionCheck =
            this.activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CALL_PHONE) }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            this.activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.CALL_PHONE),
                    123
                )
            }
        } else {
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:$tel")))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            123 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onCall(eventDto.telefono)
            } else {
                Log.d("TAG", "Call Permission Not Granted")
            }
            else -> {
            }
        }
    }


    //Alarms

    lateinit var alarmManager: AlarmManager

    private fun showCalendarAlarm() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val dpdDate = DatePickerDialog(this.requireActivity(),  DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->
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
        alarmManager = this.requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Create : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, pendingIntent)
    }

    public fun updateAlarm(milliseconds: Long){
        alarmManager = this.requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Update : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, pendingIntent)
    }

    public fun cancelAlarm(){
        alarmManager = this.requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
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