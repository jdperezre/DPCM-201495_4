package com.culturapp.culturapp

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


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
