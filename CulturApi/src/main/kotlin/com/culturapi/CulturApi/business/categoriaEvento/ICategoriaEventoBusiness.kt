package com.culturapi.CulturApi.business.categoriaEvento

import com.culturapi.CulturApi.model.CategoriaEvento


interface ICategoriaEventoBusiness {
    fun list(): MutableList<CategoriaEvento>?
    fun load(idCategoriaEvento:Long): CategoriaEvento
    fun save(categoriaEvento: CategoriaEvento): CategoriaEvento
    fun update(categoriaEvento: CategoriaEvento): CategoriaEvento
    fun remove(idCategoriaEvento: Long)
}