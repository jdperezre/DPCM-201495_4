package com.culturapi.CulturApi.model


import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name ="eventos")
data class Evento(

        val titulo: String = "",
        val organizacion: String = "",
        val descripcion: String = "",
        val telefono: String = "",
        val direccion: String = "",
        val fecha_inicio: LocalDate? = null,
        val fecha_final: LocalDate? = null,
        val hora :LocalDateTime,
        val imagen: String = "",
        val idCategoria: String = "",
        val idEstado: Long = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}