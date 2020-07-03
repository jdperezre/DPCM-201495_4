package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.evento.IEventoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException




@RestController
@RequestMapping(Constants.URL_BASE_EVENTOS)

class EventoRestController {

    @Autowired
    val eventoBusiness: IEventoBusiness? = null

    // Lista todos los eventos
    @GetMapping("/all")
    fun list(): ResponseEntity<List<Evento>> {
        return try {
            ResponseEntity(eventoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    // Obtiene evento por Id
    @GetMapping("/{id}")
    fun load(@PathVariable("id") idEvento: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(eventoBusiness!!.load(idEvento), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    // Consulta el evento por titulo
    @GetMapping("/titulo/{titulo}")
    fun findByTitulo(@PathVariable("titulo") titulo: String): ResponseEntity<Any>{
        return try{
            ResponseEntity(eventoBusiness!!.findByTitulo(titulo), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    // Consulta los eventos por IdCategoria
    @GetMapping("/categoria/{idCategoria}")
    fun findByIdCategoria(@PathVariable("idCategoria") idCategoria: String): ResponseEntity<Any>{
        return try{
            ResponseEntity(eventoBusiness!!.findByIdCategoria(idCategoria), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    // Registrar Nuevo Evento
    @PostMapping("")
    fun insert(@RequestBody evento: Evento): ResponseEntity<Any> {
        return try {
            eventoBusiness!!.save(evento)
            val responseHeaders = HttpHeaders()
            responseHeaders.set("location", Constants.URL_BASE_EVENTOS + "/" + evento.id)
            ResponseEntity(responseHeaders, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    // Actualizar el Evento
    @PutMapping("")
    fun update(@RequestBody evento: Evento): ResponseEntity<Any> {
        return try {
            eventoBusiness!!.save(evento)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    //Eliminar Evento por Id
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idEvento: Long): ResponseEntity<Any> {
        return try {
            eventoBusiness!!.remove(idEvento)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }



}