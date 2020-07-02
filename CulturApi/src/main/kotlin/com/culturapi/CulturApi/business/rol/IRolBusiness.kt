package com.culturapi.CulturApi.business.rol

import com.culturapi.CulturApi.model.Rol

interface IRolBusiness {
    fun list(): MutableList<Rol>?
    fun save(rol: Rol): Rol
}