package com.culturapp.culturapp.ui.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.new_event.*
import kotlinx.android.synthetic.main.titlebar_events.*

class NewEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.new_event)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_basic);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnBack.setOnClickListener{onBackPressed()}

        initControls()

    }

    private fun initControls() {

        editTextTitle.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextTitle.SetHint(R.string.hint_titulo);

        editTextDescription.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        editTextDescription.SetHint(R.string.hint_descripcion);

        editTextDate.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextDate.SetHint(R.string.hint_fecha);
        editTextDate.SetIcon(R.drawable.ic_calendar_input)

        editTextHour.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextHour.SetHint(R.string.hint_hora);
        editTextHour.SetIcon(R.drawable.ic_clock_input)

        editTextLocation.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextLocation.SetHint(R.string.hint_lugar);

        editTextContact.SetInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
        editTextContact.SetHint(R.string.hint_contacto);

        editTextTel.SetInputType(InputType.TYPE_CLASS_PHONE)
        editTextTel.SetHint(R.string.hint_telefono_contacto);
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }
}