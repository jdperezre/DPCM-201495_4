package com.culturapp.culturapp.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.culturapp.culturapp.LateralMenuActivity
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.titlebar_basic.*
import kotlinx.android.synthetic.main.titlebar_menu.*

class TermsConditionsActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
            setContentView(R.layout.terms_conditions)

            if (customTitleSupported) {
                supportActionBar!!.hide()
                window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
                window.setBackgroundDrawableResource(R.color.backgroundWindow)
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }
        }


}