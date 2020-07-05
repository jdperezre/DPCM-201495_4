package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.CategoriaEvento
import com.culturapi.CulturApi.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoriaEventoRepository: JpaRepository<CategoriaEvento,Long> {
    fun findByCategoriaIgnoreCase(categoria:String): Optional<CategoriaEvento>
}