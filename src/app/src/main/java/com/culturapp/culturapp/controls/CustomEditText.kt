package com.culturapp.culturapp.controls

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
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