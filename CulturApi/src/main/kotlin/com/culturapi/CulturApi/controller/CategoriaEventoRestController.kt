package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.categoriaEvento.ICategoriaEventoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.CategoriaEvento
import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CATEGORIAS)

class CategoriaEventoRestController {

    @Autowired
    val categoriaEventoBusiness: ICategoriaEventoBusiness? = null

    // Lista todas las Categorias
    @GetMapping("all")
    fun list(): ResponseEntity<List<CategoriaEvento>> {
        return try {
            ResponseEntity(categoriaEventoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Obtiene categoria por Id
    @GetMapping("/{id}")
    fun load(@PathVariable("id") idCategoriaEvento: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(categoriaEventoBusiness!!.load(idCategoriaEvento), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }

    // Registrar Categoria
    @PostMapping("")
    fun insert(@RequestBody categoriaEvento: CategoriaEvento): ResponseEntity<Any> {
        return try {
            categoriaEventoBusiness!!.save(categoriaEvento)
            val responseHeaders = HttpHeaders()
            responseHeaders.set("location", Constants.URL_BASE_EVENTOS + "/" + categoriaEvento.id)
            ResponseEntity(responseHeaders, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Actualizar Categoria
    @PutMapping("")
    fun update(@RequestBody categoriaEvento: CategoriaEvento): ResponseEntity<Any> {
        return try {
            categoriaEventoBusiness!!.save(categoriaEvento)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Eliminar categoria por Id
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idCategoriaEvento: Long): ResponseEntity<Any> {
        return try {
            categoriaEventoBusiness!!.remove(idCategoriaEvento)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }
}