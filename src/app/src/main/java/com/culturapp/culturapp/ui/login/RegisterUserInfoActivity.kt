package com.culturapp.culturapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.culturapp.culturapp.MainActivity
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.register.btnNext
import kotlinx.android.synthetic.main.register.btnPrevious
import kotlinx.android.synthetic.main.register_user_info.*
import kotlinx.android.synthetic.main.titlebar_basic.*

class RegisterUserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.register_user_info)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()
    }

    private fun initControls() {
        editTextNombres.SetHint(R.string.hint_nombres)
        editTextNombres.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME)

        editTextApellidos.SetHint(R.string.hint_apellidos)
        editTextApellidos.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME)

        editTextCelular.SetHint(R.string.hint_celular)
        editTextCelular.SetInputType(InputType.TYPE_CLASS_PHONE)

        editTextFechaNacimiento.SetInputType(InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE)
        editTextFechaNacimiento.SetHint(R.string.hint_fecha_nacimiento)
        editTextFechaNacimiento.SetIcon(R.drawable.ic_calendar_input)
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