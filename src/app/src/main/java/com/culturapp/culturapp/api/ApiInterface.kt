package com.culturapp.culturapp.api

import com.culturapp.culturapp.models.Event
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiInterface {

    @GET("eventos/all")
    fun getEvents(): Call<List<Event>>

    @POST("eventos")
    fun createEvent(@Body login: Event?): Call<String>?


/*    @GET("loginRequest/{data}")
    fun login(
        @Path("data") data: String
        ): Call<User>?*/

 /*   @GET("loginRequest/")
    fun login(
        @Query("address") address: String) : Call<String?>?*/

//    @GET("")
////    fun login(@Url url : String ) : Call<String?>


    @GET("loginRequest/{data}")
    fun login(@Path("data", encoded = true) data: JSONObject): Call<String>?

}