package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.model.Favorito

interface IFavoritoBusiness {
    fun list(): MutableList<Favorito>?
    fun save(favorito: Favorito): Favorito

}