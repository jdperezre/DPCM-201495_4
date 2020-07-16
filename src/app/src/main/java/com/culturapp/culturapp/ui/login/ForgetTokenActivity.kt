package com.culturapp.culturapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.forget_token.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.register.btnNext
import kotlinx.android.synthetic.main.register.btnPrevious
import kotlinx.android.synthetic.main.titlebar_basic.*

class ForgetTokenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.forget_token)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic)
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        initControls()
        setupEvents()
    }

    private fun initControls() {
        editTextToken.SetHint(R.string.hint_codigo)
        editTextToken.SetInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD)
    }

    private fun setupEvents() {
        btnNext.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
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