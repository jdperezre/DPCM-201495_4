package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.favorito.IEventoFavoritoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.EventoFavorito
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FAVORITO)

class EventoFavoritoRestController {

    @Autowired
    val eventoFavoritoBusiness: IEventoFavoritoBusiness? = null

    // Lista todos favoritos
    @GetMapping("/all")
    fun list(): ResponseEntity<List<EventoFavorito>> {
        return try {
            ResponseEntity(eventoFavoritoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    // Registrar Favorito
    @PostMapping("")
    fun insert(@RequestBody favorito: EventoFavorito): ResponseEntity<Any> {
        return try {
            eventoFavoritoBusiness!!.save(favorito)
            val responseHeaders = HttpHeaders()
            responseHeaders.set("location", Constants.URL_BASE_FAVORITO + "/" + favorito.id)
            ResponseEntity(responseHeaders, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    //Eliminar favorito por Id
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idFavorito: Long): ResponseEntity<Any> {
        return try {
            eventoFavoritoBusiness!!.remove(idFavorito)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }

    // Consulta por IdUsuario
    @GetMapping("/favorito/{idUsuario}")
    fun findByFavoritoIdUsuario(@PathVariable("idUsuario") idUsuario: String): ResponseEntity<Any> {
        return try{
            ResponseEntity(eventoFavoritoBusiness!!.findByIdUsuario(idUsuario), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}