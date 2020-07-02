package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.EventoFavorito
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventoFavoritoRepository : JpaRepository<EventoFavorito, Long> {
    fun findByIdFavoritoIgnoreCase(idUsuario:String): Optional<List<EventoFavorito>>
}