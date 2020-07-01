package com.culturapi.CulturApi.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(val nombre: String = "", val apellido: String = "", val celular: String = "",
                   val email: String = "",val fecha_nacimiento: LocalDate? = null,
                   val imagen: String = "",val contrasena: String = "",val idEstado: Long = 0,
                   val idRol: Long = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}