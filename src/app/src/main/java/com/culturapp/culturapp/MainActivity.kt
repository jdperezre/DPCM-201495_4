package com.culturapp.culturapp

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.culturapp.culturapp.ui.favorites.FavoritesFragment
import com.culturapp.culturapp.ui.home.HomeFragment
import com.culturapp.culturapp.ui.notifications.NotificationsFragment
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.titlebar.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.activity_main)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        nav_view.setOnItemSelectedListener {
            when(it){
                R.id.home -> showDashboardFragment()
                R.id.alarm -> showAlarmsFragment()
                R.id.favorite -> showFavoritesFragment()
            }
        }

        btnMenuLateral.setOnClickListener {
            showLateralMenu()
        }

        nav_view.setItemSelected(R.id.home)
    }

    private fun showLateralMenu(){
        startActivity(Intent(this, LateralMenuActivity::class.java))
        overridePendingTransition(R.anim.leftin, R.anim.leftout)
    }

    private fun showFavoritesFragment() {
        replaceFragment(FavoritesFragment())
    }

    private fun showAlarmsFragment() {
        replaceFragment(NotificationsFragment())
    }

    private fun showDashboardFragment() {
        replaceFragment(HomeFragment())
    }

    inline fun FragmentManager.doTransaction(func: FragmentTransaction.() ->
    FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment){
        supportFragmentManager.doTransaction { add(R.id.nav_host_fragment, fragment) }
    }


    fun AppCompatActivity.replaceFragment(fragment: Fragment) {
        supportFragmentManager.doTransaction{replace(R.id.nav_host_fragment, fragment)}
    }

    fun AppCompatActivity.removeFragment(fragment: Fragment) {
        supportFragmentManager.doTransaction{remove(fragment)}
    }

    //Popup menu
    fun menuFavorite(view: View) {
        showPopupMenu(view, R.menu.contextual_menu)
    }

    private fun showPopupMenu(view: View, menu: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_alarm -> showCalendarAlarm()
                R.id.delete_favorite -> Toast.makeText(this, "Favorito eliminado", Toast.LENGTH_SHORT).show()
            }
            true
        }
        popupMenu.show()
    }

    fun menuAlarm(view: View) {
        showPopupMenuAlarm(view, R.menu.contextual_alarm_menu)
    }

    private fun showPopupMenuAlarm(view: View, menu: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_alarm -> Toast.makeText(this, "Alarma editada", Toast.LENGTH_SHORT).show()
                R.id.delete_alarm -> Toast.makeText(this, "Alarma eliminada", Toast.LENGTH_SHORT).show()
            }
             true
        }
        popupMenu.show()
    }

    //Alarms

    lateinit var context: Context
    lateinit var alarmManager: AlarmManager


    private fun showCalendarAlarm() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

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
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Create : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + milliseconds, pendingIntent)
    }

    public fun updateAlarm(second: Long){
        //val second = edt_timer.text.toString().toInt() * 1000
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MainActivity", " Update : " + Date().toString())
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, pendingIntent)
    }

    public fun cancelAlarm(){
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
