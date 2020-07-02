package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.Rol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RolRepository: JpaRepository<Rol, Long> {
}