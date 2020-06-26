package com.culturapi.CulturApi.model


import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name ="eventos")
data class Evento(val titulo: String = "", val organizacion: String = "",
                  val descripcion: String = "",val telefono: String = "",
                  val direccion: String = "",val fecha_inicio: LocalDate? = null,
                  val fecha_final: LocalDate? = null, val imagen: String = "",
                  val idCategoria:Long = 0, val idEstado:Long = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}