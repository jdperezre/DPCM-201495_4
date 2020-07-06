package com.culturapp.culturapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.titlebar_basic.*

class ForgetEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.forget_password)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnBack.setOnClickListener{onBackPressed()}

    }
}