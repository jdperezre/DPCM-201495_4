package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.dao.EventoRepository
import com.culturapi.CulturApi.dao.FavoritoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Favorito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class FavoritoBusiness: IFavoritoBusiness {

    @Autowired
    val favoritoRepository: FavoritoRepository? = null

    @Autowired
    val eventoRepository : EventoRepository? = null

    override fun load(idUsuario: Long): List<Any> {
        val op: List<Favorito>
        var response: List<Any> = ArrayList()

        try {
            op = favoritoRepository!!.findByIdUsuario(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(op.count() == 0 ){
            throw NotFoundException("No se encuentran favoritos para este usuario = $idUsuario")
        }
        else{
            for(evento in op) {
               response += eventoRepository!!.findById(evento.idEvento)
            }
        }
        return response
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idFavorito: Long) {
        val op: Optional<Favorito>
        try {
            op = favoritoRepository!!.findById(idFavorito)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra este id de favorito = $idFavorito")
        }
        else{
            try {
                favoritoRepository!!.deleteById(idFavorito)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class)
    override fun save(favorito: Favorito): Favorito {
        val op: List<Favorito>
        try {
            op = favoritoRepository!!.findByIdUsuario(favorito.idUsuario)
            if(op.count() == 0 || (op.count() > 0 && !op.contains(favorito))) {
                return favoritoRepository!!.save(favorito)
            }
            else {
                throw BusinessException("Este evento ya se encuentra en favoritos")
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }
}