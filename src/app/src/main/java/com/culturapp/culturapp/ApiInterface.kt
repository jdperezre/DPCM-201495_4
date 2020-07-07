package com.culturapp.culturapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("eventos")
    fun list(): Call<List<DataModel>>

}