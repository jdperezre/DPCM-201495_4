package com.culturapi.CulturApi.model


import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name ="eventos")
data class Evento(val titulo: String = "", val nombre: String = "",
                  val organizacion: String = "",val telefono: String = "",
                  val direccion: String = "",val fecha_inicio: LocalDate? = null,
                  val fecha_final: LocalDate? = null){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}