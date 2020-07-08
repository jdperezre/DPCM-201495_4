package com.culturapi.CulturApi.model


import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*


@Entity
@Table(name ="eventos")
data class Evento(

        val titulo: String = "" ,
        val organizacion: String = "",
        val descripcion: String = "",
        val telefono: String = "",
        val direccion: String = "",
        val fecha_inicio: LocalDate,
        val fecha_final: LocalDate,
        val hora :LocalTime,
        val imagen: String = "",
        val idCategoria: String = "",
        val idEstado: String= ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}

