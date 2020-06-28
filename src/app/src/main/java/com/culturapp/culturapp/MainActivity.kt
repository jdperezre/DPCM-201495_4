package com.culturapp.culturapp

import android.app.Notification
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.culturapp.culturapp.ui.favorites.FavoritesFragment
import com.culturapp.culturapp.ui.home.HomeFragment
import com.culturapp.culturapp.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnItemSelectedListener {
            when(it){
                R.id.home -> ShowDashboardFragment()
                R.id.alarm -> ShowAlarmsFragment()
                R.id.favorite -> ShowFavoritesFragment()
            }
        }

        nav_view.setItemSelected(R.id.home)
    }

    private fun ShowFavoritesFragment() {
        replaceFragment(FavoritesFragment())
    }

    private fun ShowAlarmsFragment() {
        replaceFragment(NotificationsFragment())
    }

    private fun ShowDashboardFragment() {
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
    fun menuAlarm(view: View) {
        showPopupMenu(view, R.menu.contextual_menu)
    }

    private fun showPopupMenu(view: View, menu: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_alarm -> Toast.makeText(this, "Alarma editada", Toast.LENGTH_SHORT).show()
                R.id.delete_alarm -> Toast.makeText(this, "Alarma eliminada", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }
        popupMenu.show()
    }

    fun menuFavorite(view: View) {
        showPopupMenuAlarm(view, R.menu.contextual_alarm_menu)
    }

    private fun showPopupMenuAlarm(view: View, menu: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_alarm -> Toast.makeText(this, "Alarma agregada", Toast.LENGTH_SHORT).show()
                R.id.delete_favorite -> Toast.makeText(this, "Favorito eliminado", Toast.LENGTH_SHORT)
                    .show()
            }
             true
        }
        popupMenu.show()
    }

}
