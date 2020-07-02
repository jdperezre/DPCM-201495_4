package com.culturapi.CulturApi.business.estado

import com.culturapi.CulturApi.model.Estado

interface IEstadoBusiness {
    fun list(): MutableList<Estado>?
    fun save(categoriaEvento: Estado): Estado
}