package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.Evento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventoRepository:JpaRepository<Evento,Long> {
    fun findByTituloIgnoreCase(titulo:String): Optional<List<Evento>>
    fun findByIdCategoriaIgnoreCase(idCategoria:String): Optional<List<Evento>>
}