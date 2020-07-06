package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.favorito.IFavoritoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Favorito
import com.culturapi.CulturApi.model.Usuario
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FAVORITO)
class FavoritoRestController {

    @Autowired
    val favoritoBusiness: IFavoritoBusiness? = null

    // Obtiene favoritos por usuario
    @GetMapping("/Consultar/{idUsuario}")
    fun load(@PathVariable("idUsuario") idUsuario: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(favoritoBusiness!!.load(idUsuario), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }

    // Obtiene mensaje de eliminación de favorito
    @DeleteMapping("/Eliminar/{idFavorito}")
    fun remove(@PathVariable("idFavorito") idFavorito: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(favoritoBusiness!!.remove(idFavorito), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }

    // Registrar Favorito
    @PostMapping("")
    fun insert(@RequestBody favorito: Favorito): ResponseEntity<Any> {
        return try {
            favoritoBusiness!!.save(favorito)
            val responseHeaders = HttpHeaders()
            responseHeaders.set("location", Constants.URL_BASE_USUARIOS + "/" + favorito.id)
            ResponseEntity(responseHeaders, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}