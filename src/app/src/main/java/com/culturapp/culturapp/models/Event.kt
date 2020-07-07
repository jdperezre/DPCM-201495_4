package com.culturapp.culturapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Event (
    @Expose
    @SerializedName("id")
    val id: Integer,
    @Expose
    @SerializedName("titulo")
    val titulo: String,
    @Expose
    @SerializedName("organizacion")
    val organizacion: String,
    @Expose
    @SerializedName("descripcion")
    val descripcion: String,
    @Expose
    @SerializedName("telefono")
    val telefono: String,
    @Expose
    @SerializedName("direccion")
    val direccion: String,
    @Expose
    @SerializedName("fecha_inicio")
    val fechaInicio: String,
    @Expose
    @SerializedName("hora")
    val hora: String,
    @Expose
    @SerializedName("imagen")
    val imagen: String,
    @Expose
    @SerializedName("idCategoria")
    val idCategoria: String,
    @Expose
    @SerializedName("idEstado")
    val idEstado: String
)