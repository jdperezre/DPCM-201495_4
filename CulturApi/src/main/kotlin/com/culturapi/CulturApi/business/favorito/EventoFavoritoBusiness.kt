package com.culturapi.CulturApi.business.favorito

import com.culturapi.CulturApi.dao.EventoFavoritoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.EventoFavorito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventoFavoritoBusiness: IEventoFavoritoBusiness {

    @Autowired
    val eventoFavoritoRepository: EventoFavoritoRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<EventoFavorito>? {
        try {
            return eventoFavoritoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun save(favorito: EventoFavorito): EventoFavorito {
        try {
            return eventoFavoritoRepository!!.save(favorito)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idFavorito: Long) {
        val op: Optional<EventoFavorito>

        try {
            op = eventoFavoritoRepository!!.findById(idFavorito)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (!op.isPresent){
            throw NotFoundException("No se encuentra el evento favorito con este id=$idFavorito")
        }else{
            try {
                eventoFavoritoRepository!!.deleteById(idFavorito)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun findByIdUsuario(idUsuario: String): List<EventoFavorito> {
        val op: Optional<List<EventoFavorito>>

        try{
            op = eventoFavoritoRepository!!.findByIdFavoritoIgnoreCase(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)

        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra el Usuario Id= $idUsuario")
        }

        return op.get()
    }
}