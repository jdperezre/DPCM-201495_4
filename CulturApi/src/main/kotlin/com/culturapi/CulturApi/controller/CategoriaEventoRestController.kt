package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.categoriaEvento.ICategoriaEventoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.model.CategoriaEvento
import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_CATEGORIAS)
class CategoriaEventoRestController {
    @Autowired
    val categoriaEventoBusiness: ICategoriaEventoBusiness? = null

    // Lista todas las Categorias
    @GetMapping("")
    fun list(): ResponseEntity<List<CategoriaEvento>> {
        return try {
            ResponseEntity(categoriaEventoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
}