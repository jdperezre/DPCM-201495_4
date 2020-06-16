package com.culturapp.culturapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onResume() {
        customEditText.setHint(R.string.hint_custom_edittext);
        customEditText.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_home_black_24dp,0)
        customEditText.drawable
        super.onResume()
    }
}
