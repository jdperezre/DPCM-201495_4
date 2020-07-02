package com.culturapi.CulturApi.business.evento

import com.culturapi.CulturApi.model.Evento

interface IEventoBusiness {
    fun list(): MutableList<Evento>?
    fun load(idEvento:Long): Evento
    fun save(evento: Evento):Evento
    fun remove(idEvento: Long)
    fun findByTitulo(titulo:String): List<Evento>
    fun findByIdCategoria(idCategoria:String): List<Evento>
    fun findByFecha(idCategoria:String): List<Evento>
}