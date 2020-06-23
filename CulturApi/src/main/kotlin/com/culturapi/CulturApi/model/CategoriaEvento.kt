package com.culturapi.CulturApi.model

import javax.persistence.*

@Entity
@Table(name = "categoriaEventos")
data class CategoriaEvento(val categoria: String = "", val descripcion: String = "",
                           val icono: String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}