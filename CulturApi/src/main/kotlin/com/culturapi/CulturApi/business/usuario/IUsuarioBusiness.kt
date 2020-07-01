package com.culturapi.CulturApi.business.usuario

import com.culturapi.CulturApi.model.Usuario

interface IUsuarioBusiness {
    fun list(): MutableList<Usuario>?
    fun load(idUsuario:Long): Usuario
    fun save(usuario: Usuario): Usuario
    fun remove(idUsuario: Long)
}