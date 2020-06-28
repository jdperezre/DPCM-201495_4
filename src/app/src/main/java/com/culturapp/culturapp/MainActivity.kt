package com.culturapp.culturapp

import android.app.Notification
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.culturapp.culturapp.ui.dashboard.DashboardFragment
import com.culturapp.culturapp.ui.home.HomeFragment
import com.culturapp.culturapp.ui.notifications.NotificationsFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val appBarConfiguration = AppBarConfiguration(setOf(
        //        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController( navController : BottomNavigationView)
        nav_view.setOnItemSelectedListener {
            when(it){
                R.id.home -> ShowDashboardFragment()
                R.id.alarm -> ShowAlarmsFragment()
                R.id.favorite -> ShowNotificationsFragment()
            }
        }

        nav_view.setItemSelected(R.id.home)
    }

    private fun ShowNotificationsFragment() {
        replaceFragment(NotificationsFragment())
    }

    private fun ShowAlarmsFragment() {
        replaceFragment(HomeFragment())
    }

    private fun ShowDashboardFragment() {
        replaceFragment(DashboardFragment())
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

}
