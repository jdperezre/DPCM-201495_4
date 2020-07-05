package com.culturapi.CulturApi.model

import javax.persistence.*

@Table(name = "loginResponse")
data class LoginResponse(var result: Boolean = false, var rol: Long = 0,
                         var descripcion: String = ""){
}