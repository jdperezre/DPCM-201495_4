package com.culturapi.CulturApi.business.evento

import com.culturapi.CulturApi.dao.EventoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Evento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventoBusiness: IEventoBusiness {

    @Autowired
    val eventoRepository : EventoRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<Evento>? {
        try {
            return eventoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idEvento: Long): Evento {
        val op: Optional<Evento>
        try {
            op = eventoRepository!!.findById(idEvento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra el evento con este id =$idEvento")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(evento: Evento): Evento {
        try {
            return eventoRepository!!.save(evento)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idEvento: Long) {
        val op: Optional<Evento>

        try {
            op = eventoRepository!!.findById(idEvento)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (!op.isPresent){
            throw NotFoundException("No se encuentra el evento con id=$idEvento")
        }else{
            try {
                eventoRepository!!.deleteById(idEvento)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun findByNombre(nombre: String): List<Evento> {
        val op: Optional<List<Evento>>

        try{
            op = eventoRepository!!.findByNombreIgnoreCase(nombre)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra la persona con el nombre = $nombre")
        }

        return op.get()
    }
}