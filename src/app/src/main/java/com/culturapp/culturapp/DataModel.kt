package com.culturapp.culturapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import java.util.*

data class DataModel(

    @Expose
    @SerializedName("id")
    val id: Integer,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("description")
    val thumbnailurl: String
)