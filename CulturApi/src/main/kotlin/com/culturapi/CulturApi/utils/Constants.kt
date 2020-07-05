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

        //Base API endpoint para Usuarios
        const val URL_BASE_USUARIOS = "$URL_BASE/usuarios"

        //Base API endpoint para Estados
        const val URL_BASE_ESTADO = "$URL_BASE/estados"

        //Base API endpoint para Roles
        const val URL_BASE_ROL = "$URL_BASE/roles"

        //Base API endpoint para Favoritos
        const val URL_BASE_FAVORITO = "$URL_BASE/favoritos"

        //Base API endpoint para Login
        const val URL_BASE_LOGIN = "$URL_BASE/loginRequest"

        //Usuario inactivo
        const val USUARIO_INACTIVO = "Usuario inactivo"

        //Contraseña Invalida
        const val CONTRASENA_INVALIDA = "Contraseña invalida"

        //Contraseña Valida y Usuario Activo
        const val ACCESO_APROBADO = "¡Bienvenido!"

        //Status de inactivo
        const val INACTIVO = 2
    }

}