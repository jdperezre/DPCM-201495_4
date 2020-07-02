package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name ="roles")
data class Rol(val nombrerol: String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}