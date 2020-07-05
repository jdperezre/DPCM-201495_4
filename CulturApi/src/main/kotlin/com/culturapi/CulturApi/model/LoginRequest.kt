package com.culturapi.CulturApi.model

import javax.persistence.*

@Table(name = "loginRequest")
data class LoginRequest(val email: String = "", val contrasena: String = ""){
}