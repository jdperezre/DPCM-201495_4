package com.culturapp.culturapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.editTextPassword
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.titlebar_basic.*

class RegisterLoginInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.register)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()

    }

    private fun initControls() {
        editTextEmail.SetHint(R.string.hint_usuario)
        editTextEmail.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)

        editTextPassword.SetHint(R.string.hint_contrasena)
        editTextPassword.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

        editTextConfirm.SetHint(R.string.hint_confirm)
        editTextConfirm.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }

    private fun setupEvents() {
        txtDescription.setOnClickListener{startTermsActivity()}
        btnNext.setOnClickListener {
            startActivity(Intent(this, RegisterUserInfoActivity::class.java))
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
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }

    fun startTermsActivity() {
        startActivity(Intent(this, TermsConditionsActivity::class.java))
        overridePendingTransition(R.anim.leftin, R.anim.leftout)
    }
}