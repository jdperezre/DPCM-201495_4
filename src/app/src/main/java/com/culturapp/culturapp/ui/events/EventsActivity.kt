package com.culturapp.culturapp.ui.events

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.FragmentActivity
import com.culturapp.culturapp.R
import com.culturapp.culturapp.adapters.EventListAdapter
import com.culturapp.culturapp.api.ApiClient
import com.culturapp.culturapp.models.Event
import kotlinx.android.synthetic.main.events.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.titlebar_events.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsActivity : AppCompatActivity() {

    lateinit var progressProgressDialog: ProgressDialog
    var dataList = ArrayList<Event>()
    lateinit var adapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customTitleSupported =  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        setContentView(R.layout.events)

        if (customTitleSupported) {
            supportActionBar!!.hide()
            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar_events);
            window.setBackgroundDrawableResource(R.color.backgroundWindow)
        }

        btnBack.setOnClickListener{onBackPressed()}

        btnAdd.setOnClickListener { showAddEvent() }
    }

    override fun onResume() {
        super.onResume()


        progressProgressDialog = ProgressDialog(this, 0)
        progressProgressDialog.run {
            setTitle("Cargando")
            setContentView(R.layout.progress)
            setCancelable(false)
            show()
        }
        getData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.rigthin, R.anim.rigthout);
    }

    private fun showAddEvent() {
        startActivity(Intent(this, NewEventActivity::class.java))
        overridePendingTransition(R.anim.leftin, R.anim.leftout)
    }


    private fun getData() {
        val call: Call<List<Event>> = ApiClient.getClient.getEvents()
        call.enqueue(object : Callback<List<Event>> {

            override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                progressProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!)
                adapter = EventListAdapter(this@EventsActivity as FragmentActivity, response!!.body()!!)

                events_listView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                progressProgressDialog.dismiss()
            }

        })
    }


}