package com.culturapp.culturapp.controls

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