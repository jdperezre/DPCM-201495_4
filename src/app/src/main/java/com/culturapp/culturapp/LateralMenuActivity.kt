package com.culturapp.culturapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import com.culturapp.culturapp.ui.events.EventsActivity
import kotlinx.android.synthetic.main.lateral_menu.*

class LateralMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.lateral_menu)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_menu)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnClose.setOnClickListener {
            onBackPressed()
        }

        btnLogin.setOnClickListener { showLogin() }
        btnProfile.setOnClickListener { showProfile() }
        btnEvents.setOnClickListener { showEvents() }
        btnLogout.setOnClickListener { showConfirmLogout() }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout)
    }

    private fun showLogin(){

    }

    private fun showProfile(){

    }

    private fun showEvents(){
        startActivity(Intent(this, EventsActivity::class.java))
        finish()
        overridePendingTransition(R.anim.leftin, R.anim.leftout)
    }

    private fun showConfirmLogout(){
        logout()
    }

    fun logout(){

    }
}