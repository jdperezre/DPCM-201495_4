package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.estado.IEstadoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.model.Estado
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ESTADO)

class EstadoRestController {

    @Autowired
    val estadoBusiness: IEstadoBusiness? = null

    // Lista todos los estados
    @GetMapping("")
    fun list(): ResponseEntity<List<Estado>> {
        return try {
            ResponseEntity(estadoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}