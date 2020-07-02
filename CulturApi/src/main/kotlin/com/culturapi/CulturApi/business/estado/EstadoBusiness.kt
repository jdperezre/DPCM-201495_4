package com.culturapi.CulturApi.business.estado

import com.culturapi.CulturApi.dao.EstadoRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.model.Estado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EstadoBusiness: IEstadoBusiness {

    @Autowired
    val estadoRepository: EstadoRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<Estado>? {
        try {
            return estadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun save(estado: Estado): Estado {
        try {
            return estadoRepository!!.save(estado)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }
}