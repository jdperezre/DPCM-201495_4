package com.culturapp.culturapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
    override fun onResume() {
        super.onResume()
        var adapter = EventListAdapter(this.requireActivity(), generateData())

        events_favorites_listView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }





}


