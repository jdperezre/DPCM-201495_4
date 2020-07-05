package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.Login.ILoginBusiness
import com.culturapi.CulturApi.business.usuario.IUsuarioBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.LoginRequest
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_LOGIN)
class LoginRestController {

    @Autowired
    val loginBusiness: ILoginBusiness? = null

    // Obtener respuesta de Logueo
    @GetMapping("/{login}")
    fun load(@PathVariable("login") loginRequest: String): ResponseEntity<Any> {
        return try {
            ResponseEntity(loginBusiness!!.load(loginRequest), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}