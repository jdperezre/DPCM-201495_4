package com.culturapp.culturapp.ui.favorites

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListAdapter
import com.culturapp.culturapp.adapters.EventListHomeAdapter
import com.culturapp.culturapp.api.ApiClient
import com.culturapp.culturapp.models.Event
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesFragment : Fragment() {

    lateinit var progressProgressDialog: ProgressDialog
    var dataList = ArrayList<Event>()
    lateinit var adapter: EventListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
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
                    Toast.makeText(this@FavoritesFragment.requireActivity(), getString(R.string.result_not_found), Toast.LENGTH_LONG).show()
                    return
                }

                dataList.addAll(response.body()!!)
                adapter = EventListAdapter(this@FavoritesFragment.requireActivity(), response.body()!!)

                events_favorites_listView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                progressProgressDialog.dismiss()
            }

        })
    }



}


