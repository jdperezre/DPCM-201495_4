package com.culturapp.culturapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import com.culturapp.culturapp.MainActivity
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.register.editTextPassword
import kotlinx.android.synthetic.main.titlebar_basic.*

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.forget_password)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()
    }

    private fun initControls() {
        editTextPassword.SetHint(R.string.hint_contrasena)
        editTextPassword.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

        editTextConfirm.SetHint(R.string.hint_confirm)
        editTextConfirm.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }

    private fun setupEvents() {
        btnNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            this.finishAffinity()
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