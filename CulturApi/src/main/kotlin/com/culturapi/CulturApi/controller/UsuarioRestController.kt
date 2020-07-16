package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.usuario.IUsuarioBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Usuario
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_USUARIOS)

class UsuarioRestController {

    @Autowired
    val usuarioBusiness: IUsuarioBusiness? = null

    // Lista todos los usuarios
    @GetMapping("all")
    fun list(): ResponseEntity<List<Usuario>> {
        return try {
            ResponseEntity(usuarioBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Obtiene usuario por Id
    @GetMapping("/{id}")
    fun load(@PathVariable("id") idUsuario: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity(usuarioBusiness!!.load(idUsuario), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }

    // Registrar Usuario
    @PostMapping("")
    fun insert(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        return try {
            usuarioBusiness!!.save(usuario)
            val responseHeaders = HttpHeaders()
            responseHeaders.set("location", Constants.URL_BASE_USUARIOS + "/" + usuario.id)
            ResponseEntity(responseHeaders, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Actualizar usuario
    @PutMapping("")
    fun update(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        return try {
            usuarioBusiness!!.save(usuario)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Eliminar usuario por Id
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idUsuario: Long): ResponseEntity<Any> {
        return try {
            usuarioBusiness!!.remove(idUsuario)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(e.message,HttpStatus.NOT_FOUND)
        }
    }
}