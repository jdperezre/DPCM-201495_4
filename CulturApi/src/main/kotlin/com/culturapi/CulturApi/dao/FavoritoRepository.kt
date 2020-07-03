package com.culturapi.CulturApi.dao

import com.culturapi.CulturApi.model.Favorito
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FavoritoRepository: JpaRepository<Favorito, Long> {

}