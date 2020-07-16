package com.culturapp.culturapp.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListHomeAdapter
import com.culturapp.culturapp.api.ApiClient
import com.culturapp.culturapp.models.Event
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class HomeFragment : Fragment() {

    lateinit var progressProgressDialog: ProgressDialog
    var dataList = ArrayList<Event>()
    lateinit var adapter: EventListHomeAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()

        progressProgressDialog = ProgressDialog(this.requireContext(), 0)
        progressProgressDialog.run {
            setTitle(getString(R.string.loading))
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

                if(response!!.body().isNullOrEmpty()){
                    Toast.makeText(this@HomeFragment.requireActivity(), getString(R.string.result_not_found), Toast.LENGTH_LONG).show()
                    return
                }

                dataList.addAll(response!!.body()!!)
                adapter = EventListHomeAdapter(this@HomeFragment.requireActivity(), response!!.body()!!)

                events_listView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                progressProgressDialog.dismiss()
            }

        })
    }
}
