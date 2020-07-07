package com.culturapp.culturapp.ui.notifications

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListNotificationAdapter
import com.culturapp.culturapp.api.ApiClient
import com.culturapp.culturapp.models.Event

import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    lateinit var progressProgressDialog: ProgressDialog
    var dataList = ArrayList<Event>()
    lateinit var adapter: EventListNotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onResume() {
        super.onResume()

        progressProgressDialog = ProgressDialog(this.requireContext(), 0)
        progressProgressDialog.run {
            setTitle("Cargando")
            setContentView(R.layout.progress)
            setCancelable(false)
            show()
        }
        getData()
    }


    private fun getData() {
        val call: Call<List<Event>> = ApiClient.getClient.getEvents()
        call.enqueue(object : Callback<List<Event>> {

            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                progressProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!)
                adapter = EventListNotificationAdapter(this@NotificationsFragment.requireActivity(), response!!.body()!!)

                events_notification_listView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                progressProgressDialog.dismiss()
            }

        })
    }
}

