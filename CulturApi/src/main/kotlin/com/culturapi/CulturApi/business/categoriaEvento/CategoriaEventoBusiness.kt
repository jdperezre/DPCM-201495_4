package com.culturapi.CulturApi.business.categoriaEvento

import com.culturapi.CulturApi.dao.CategoriaEventoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.CategoriaEvento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoriaEventoBusiness: ICategoriaEventoBusiness {

    @Autowired
    val categoriaEventoRepository: CategoriaEventoRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<CategoriaEvento>? {
       try {
            return categoriaEventoRepository!!.findAll()
       }catch (e:Exception){
            throw BusinessException(e.message)
       }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idCategoriaEvento: Long): CategoriaEvento {
        val op: Optional<CategoriaEvento>
        try {
            op = categoriaEventoRepository!!.findById(idCategoriaEvento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra la categoria con este id =$idCategoriaEvento")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(categoriaEvento: CategoriaEvento): CategoriaEvento {
        val op: Optional<CategoriaEvento>
        try {
            op = categoriaEventoRepository!!.findByCategoriaIgnoreCase(categoriaEvento.categoria)
            if(!op.isPresent) {
                return categoriaEventoRepository!!.save(categoriaEvento)
            }
            else {
                throw BusinessException("Esta categoria ya se encuentra registrada")
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idCategoriaEvento: Long) {
        val op: Optional<CategoriaEvento>

        try {
            op = categoriaEventoRepository!!.findById(idCategoriaEvento)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (!op.isPresent){
            throw NotFoundException("No se encuentra el evento con id=$idCategoriaEvento")
        }else{
            try {
                categoriaEventoRepository!!.deleteById(idCategoriaEvento)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }
}