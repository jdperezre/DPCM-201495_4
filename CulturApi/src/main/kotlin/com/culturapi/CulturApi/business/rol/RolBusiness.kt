package com.culturapi.CulturApi.business.rol

import com.culturapi.CulturApi.dao.RolRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.model.Rol
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RolBusiness: IRolBusiness {

    @Autowired
    val rolRepository: RolRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<Rol>? {
        try {
            return rolRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun save(rol: Rol): Rol {
        try {
            return rolRepository!!.save(rol)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

}