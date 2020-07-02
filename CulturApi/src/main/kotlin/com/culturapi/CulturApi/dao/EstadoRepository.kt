package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.Estado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EstadoRepository :JpaRepository<Estado,Long>{
}
