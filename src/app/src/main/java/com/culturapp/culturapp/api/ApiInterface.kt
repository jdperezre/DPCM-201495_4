package com.culturapp.culturapp.api

import com.culturapp.culturapp.models.Event
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("eventos/all")
    fun getEvents(): Call<List<Event>>

    @POST("eventos")
    fun createEvent(@Body login: Event?): Call<Event?>?

}