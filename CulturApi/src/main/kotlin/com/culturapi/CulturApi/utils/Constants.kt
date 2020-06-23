package com.culturapi.CulturApi.utils

class Constants {

    companion object {
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION

        //Base API endpoint para eventos
        const val URL_BASE_EVENTOS = "$URL_BASE/eventos"

        //Base API endpoint para Categorias
        const val URL_BASE_CATEGORIAS = "$URL_BASE/categoriasEventos"


    }

}