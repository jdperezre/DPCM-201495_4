package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.CategoriaEvento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaEventoRepository: JpaRepository<CategoriaEvento,Long> {
}