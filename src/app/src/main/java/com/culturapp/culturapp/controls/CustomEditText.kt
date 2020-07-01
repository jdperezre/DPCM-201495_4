package com.culturapp.culturapp.controls

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.custom_edittext.view.*
import kotlinx.android.synthetic.main.new_event.*
import java.util.*

class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_edittext, this, true)
        orientation = VERTICAL
    }

    fun setText(str:String) {
        customEditText.setText(str)
    }
    fun SetInputType(type: Int){
        customEditText.inputType = type

        if(type == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        {
            val scale = context.resources.displayMetrics.density
            val widthScale = (300 * scale + 0.5f)
            val heightScale = (100 * scale + 0.5f)
            val marginScale = (16 * scale + 0.5f)
            val params = LayoutParams(widthScale.toInt(), heightScale.toInt())
            params.topMargin = marginScale.toInt()
            customEditText.layoutParams = params

            customEditText.isSingleLine = false
            customEditText.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION;
            customEditText.setLines(5);
            customEditText.maxLines = 50;
            customEditText.minLines = 5;
            customEditText.isVerticalScrollBarEnabled = true;
            customEditText.movementMethod = ScrollingMovementMethod.getInstance();
            customEditText.scrollBarStyle = View.SCROLLBARS_INSIDE_INSET;

            customEditText.isElegantTextHeight = true
        }
        else if(type == InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE){
            customEditText.isClickable = true
            customEditText.isFocusable = false
            customEditText.isFocusableInTouchMode = false

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            customEditText.setOnClickListener {
                val dpd = DatePickerDialog(this.context,  DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->
                    customEditText.setText("$mDay/$mMonth/$mYear")
                }, year, month, day)
                dpd.show()
            }
        }
        else if(type == InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_TIME){
            customEditText.isClickable = true
            customEditText.isFocusable = false
            customEditText.isFocusableInTouchMode = false

            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)

            customEditText.setOnClickListener {
                val dpd = TimePickerDialog(this.context,  TimePickerDialog.OnTimeSetListener{ view, mHour, mSecond->
                    customEditText.setText("$mHour:$mSecond")
                }, hour, minute, false)
                dpd.show()
            }
        }
    }

    fun SetHint(resId: Int){
        customEditText.setHint(resId)
    }

    fun SetIcon(resId: Int ){
        val tintColor = ResourcesCompat.getColor(resources, R.color.gray_icons, null)
        var iconDrawable = ResourcesCompat.getDrawable(resources, resId, null)!!
        iconDrawable = DrawableCompat.wrap(iconDrawable)
        DrawableCompat.setTint(iconDrawable, tintColor)

        iconDrawable.setBounds(0, 0, iconDrawable.intrinsicWidth, iconDrawable.intrinsicHeight)

        customEditText.setCompoundDrawables(null, null, iconDrawable, null)
    }
}