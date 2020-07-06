package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.model.Favorito
import com.culturapi.CulturApi.model.Usuario
import java.util.*

interface IFavoritoBusiness {
    fun load(idUsuario: Long): List<Favorito>
    fun save(favorito: Favorito): Favorito
    fun remove(idFavorito: Long)
}