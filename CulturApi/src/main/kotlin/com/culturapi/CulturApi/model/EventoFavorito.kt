package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name ="favoritos")
data class EventoFavorito(val idevento: String = "", val idusuario: String = "") {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}