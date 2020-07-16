package com.culturapi.CulturApi.model

import java.time.LocalDate
import javax.persistence.*

@Table(name = "loginResponse")
data class LoginResponse(
        var result: Boolean = false,
        var descripcion: String = "",
        var nombre: String = "",
        var apellido: String = "",
        var celular: String = "",
        var email: String = "",
        var fecha_nacimiento: LocalDate? = null,
        var imagen: String = "",
        var idRol: Long = 0,
        var idEstado: Long = 0,
        var id: Long = 0){
}