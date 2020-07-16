package com.culturapi.CulturApi.business.Login

import com.culturapi.CulturApi.dao.UsuarioRepository
import com.culturapi.CulturApi.exception.BusinessException

import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.LoginRequest
import com.culturapi.CulturApi.model.LoginResponse
import com.culturapi.CulturApi.model.Usuario
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class LoginBusiness:ILoginBusiness {

    @Autowired
    val usuarioRepository : UsuarioRepository? = null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(loginRequest: LoginRequest): LoginResponse {

        val op: Optional<Usuario>
        val response = LoginResponse()

        try {
            op = usuarioRepository!!.findByEmailIgnoreCase(loginRequest.email)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            response.result = false
            response.descripcion = Constants.CONTRASENA_INVALIDA
        }
        else{

            response.result = false
            if(op.get().contrasena != loginRequest.contrasena &&
                    op.get().idEstado.toInt() == Constants.INACTIVO){
                response.descripcion = Constants.USUARIO_INACTIVO + " y " + Constants.CONTRASENA_INVALIDA
            }
            else if(op.get().contrasena != loginRequest.contrasena){
                response.descripcion = Constants.CONTRASENA_INVALIDA
            }
            else if(op.get().idEstado.toInt() == Constants.INACTIVO){
                response.descripcion = Constants.USUARIO_INACTIVO
            }
            else{
                response.nombre = op.get().nombre
                response.apellido = op.get().apellido
                response.celular = op.get().celular
                response.email = op.get().email
                response.fecha_nacimiento = op.get().fecha_nacimiento
                response.imagen = op.get().imagen
                response.idRol = op.get().idRol
                response.idEstado = op.get().idEstado
                response.id = op.get().id
                response.result = true
                response.descripcion = Constants.ACCESO_APROBADO
            }
        }
        return response
    }
}