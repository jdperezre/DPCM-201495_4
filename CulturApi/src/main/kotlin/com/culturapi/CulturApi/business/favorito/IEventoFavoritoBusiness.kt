package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.model.EventoFavorito

interface IEventoFavoritoBusiness {
    fun list(): MutableList<EventoFavorito>?
    fun save(favorito: EventoFavorito): EventoFavorito
    fun remove(idFavorito: Long)
    fun findByIdUsuario(idUsuario:String): List<EventoFavorito>
}