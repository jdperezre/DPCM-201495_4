package com.culturapp.culturapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.new_event.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile_content.*
import kotlinx.android.synthetic.main.titlebar_events.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.profile)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnBack.setOnClickListener{onBackPressed()}

        initControls()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }

    private fun initControls() {

        editTextCellphone.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextCellphone.SetHint(R.string.hint_cellphone);

        editTextBirthDate.SetInputType(InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE)
        editTextBirthDate.SetHint(R.string.hint_birth_date);
        editTextBirthDate.SetIcon(R.drawable.ic_calendar_input)

        editTextPassword.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        editTextPassword.SetHint(R.string.hint_password);

        editTextState.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        editTextState.SetHint(R.string.hint_state);

    }
}