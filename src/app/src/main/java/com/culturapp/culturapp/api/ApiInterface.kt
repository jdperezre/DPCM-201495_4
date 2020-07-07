package com.culturapp.culturapp.api

import com.culturapp.culturapp.models.Event
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("eventos/all")
    fun getEvents(): Call<List<Event>>

}