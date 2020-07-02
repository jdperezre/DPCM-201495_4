package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name ="Estados")
data class Estado(val nombreestado: String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}