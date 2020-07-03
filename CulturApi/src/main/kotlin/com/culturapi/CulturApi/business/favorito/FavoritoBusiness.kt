package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.dao.FavoritoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Favorito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FavoritoBusiness: IFavoritoBusiness {

    @Autowired
    val favoritoRepository: FavoritoRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<Favorito>? {
        try {
            return favoritoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun save(favorito: Favorito): Favorito {
        try {
            return favoritoRepository!!.save(favorito)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }



}