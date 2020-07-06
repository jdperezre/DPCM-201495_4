package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name ="favoritos")
data class Favorito (
        val idUsuario: Long = 0,
        val idEvento: Long = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}