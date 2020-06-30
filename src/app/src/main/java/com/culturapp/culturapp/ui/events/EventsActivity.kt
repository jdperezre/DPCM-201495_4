package com.culturapp.culturapp.ui.events

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.titlebar_events.*

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.events)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_events);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnBack.setOnClickListener{onBackPressed()}

        btnAdd.setOnClickListener { showAddEvent() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }

    private fun showAddEvent() {
        startActivity(Intent(this, NewEventActivity::class.java))
        overridePendingTransition(R.anim.leftin, R.anim.leftout)
    }
}