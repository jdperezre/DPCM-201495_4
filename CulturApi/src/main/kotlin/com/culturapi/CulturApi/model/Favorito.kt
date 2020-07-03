package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name ="favoritos")
data class Favorito (
        val id_Usuario: String = "",
        val id_Evento: String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}