package com.culturapi.CulturApi.controller

import com.culturapi.CulturApi.business.favorito.IFavoritoBusiness
import com.culturapi.CulturApi.exception.BusinessException
import com.culturapi.CulturApi.exception.NotFoundException
import com.culturapi.CulturApi.model.Favorito
import com.culturapi.CulturApi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_FAVORITO)
class FavoritoRestController {

    @Autowired
    val favoritoBusiness: IFavoritoBusiness? = null

    // Lista todos favoritos
    @GetMapping("/all")
    fun list(): ResponseEntity<List<Favorito>> {
        return try {
            ResponseEntity(favoritoBusiness!!.list(), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


}