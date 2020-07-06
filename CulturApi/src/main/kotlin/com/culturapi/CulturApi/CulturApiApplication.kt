package com.culturapi.CulturApi

import com.culturapi.CulturApi.dao.EventoRepository
import com.culturapi.CulturApi.dao.UsuarioRepository
import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class CulturApiApplication:CommandLineRunner{

	@Autowired
	val usuarioRepository: UsuarioRepository? = null

	override fun run(vararg args: String?) {

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

		val usuario1 = Usuario("Sebastian", "Castano", "3108238608",
				"sebas1929castano@gmail.com",LocalDate.parse("29-01-1996", formatter),"hola",
				"sebas123",2,2)

	}
}


fun main(args: Array<String>) {
	runApplication<CulturApiApplication>(*args)
}
