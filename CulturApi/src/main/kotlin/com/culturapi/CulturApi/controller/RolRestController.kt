package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.rol.IRolBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.model.Rol
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_ROL)
class RolRestController {
    @Autowired
    val rolBusiness: IRolBusiness? = null

    // Lista todos los roles
    @GetMapping("")
    fun list(): ResponseEntity<List<Rol>> {
        return try {
            ResponseEntity(rolBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}