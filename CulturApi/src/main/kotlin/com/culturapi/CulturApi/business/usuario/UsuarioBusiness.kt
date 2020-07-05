package com.culturapi.CulturApi.business.usuario

import com.culturapi.CulturApi.dao.UsuarioRepository
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioBusiness: IUsuarioBusiness {

    @Autowired
    val usuarioRepository : UsuarioRepository? = null

    @Throws(BusinessException::class)
    override fun list(): MutableList<Usuario>? {
        try {
            return usuarioRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idUsuario: Long): Usuario {
        val op: Optional<Usuario>
        try {
            op = usuarioRepository!!.findById(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra el usuario con este id =$idUsuario")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(usuario: Usuario): Usuario {
        val op: Optional<Usuario>
        try {
            op = usuarioRepository!!.findByEmailIgnoreCase(usuario.email)
            if(!op.isPresent) {
                return usuarioRepository!!.save(usuario)
            }
            else {
                throw BusinessException("Este email ya se encuentra registrado")
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idUsuario: Long) {
        val op: Optional<Usuario>

        try {
            op = usuarioRepository!!.findById(idUsuario)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

        if (!op.isPresent){
            throw NotFoundException("No se encuentra el usuario con id=$idUsuario")
        }else{
            try {
                usuarioRepository!!.deleteById(idUsuario)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }
}