package com.culturapi.CulturApi.business.Login

import com.culturapi.CulturApi.dao.UsuarioRepository
import com.culturapi.CulturApi.exception.BusinessException

import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.LoginRequest
import com.culturapi.CulturApi.model.LoginResponse
import com.culturapi.CulturApi.model.Usuario
import com.culturapi.CulturApi.utils.Constants
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*


@Service
class LoginBusiness:ILoginBusiness {

    @Autowired
    val usuarioRepository : UsuarioRepository? = null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(loginRequest: String): LoginResponse {

        val gson =  Gson()
        val op: Optional<Usuario>
        val response = LoginResponse()

        val login = gson.fromJson(loginRequest,LoginRequest::class.java)

        try {
            op = usuarioRepository!!.findByEmailIgnoreCase(login.email)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encuentra el usuario con este email =${login.email}")
        }
        else{

            response.result = false
            response.rol = op.get().idRol
            if(op.get().contrasena != login.contrasena &&
                    op.get().idEstado.toInt() == Constants.INACTIVO){
                response.descripcion = Constants.USUARIO_INACTIVO + " y " + Constants.CONTRASENA_INVALIDA
            }
            else if(op.get().contrasena != login.contrasena){
                response.descripcion = Constants.CONTRASENA_INVALIDA
            }
            else if(op.get().idEstado.toInt() == Constants.INACTIVO){
                response.descripcion = Constants.USUARIO_INACTIVO
            }
            else{
                response.result = true
                response.descripcion = Constants.ACCESO_APROBADO
            }
        }
        return response
    }
}