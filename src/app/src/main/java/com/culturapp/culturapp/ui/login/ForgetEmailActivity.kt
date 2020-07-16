package com.culturapp.culturapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.register.editTextPassword
import kotlinx.android.synthetic.main.titlebar_basic.*

class ForgetEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.forget_email)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()
    }

    private fun initControls() {
        editTextEmail.SetHint(R.string.hint_email)
        editTextEmail.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
    }

    private fun setupEvents() {
        btnNext.setOnClickListener {
            startActivity(Intent(this, ForgetTokenActivity::class.java))
            overridePendingTransition(R.anim.leftin, R.anim.leftout)
        }

        btnPrevious.setOnClickListener {
            onBackPressed()
        }

        btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout)
    }
}