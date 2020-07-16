package com.culturapi.CulturApi

import com.culturapi.CulturApi.dao.EstadoRepository
import com.culturapi.CulturApi.dao.EventoRepository
import com.culturapi.CulturApi.dao.RolRepository
import com.culturapi.CulturApi.model.Estado
import com.culturapi.CulturApi.dao.UsuarioRepository
import com.culturapi.CulturApi.model.Evento
import com.culturapi.CulturApi.model.Rol
import com.culturapi.CulturApi.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootApplication
class CulturApiApplication:CommandLineRunner{

	/*
	@Autowired
	val eventoRepository: EventoRepository? = null

	@Autowired
	val estadoRepository: EstadoRepository? = null

	@Autowired
	val rolRepository: RolRepository? = null

	 */
	val usuarioRepository: UsuarioRepository? = null

	override fun run(vararg args: String?) {
		/*
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val evento = Evento("Rock al parque", "Eventos S.A.S",
				"Evento cultural y ludico","3100000",
				"Km 15 via el Roble", LocalDate.parse("25-11-2020", formatter),
				LocalDate.parse("27-11-2020", formatter), LocalDateTime.now(),"img.jpg","1","2")

		eventoRepository!!.save(evento)

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

		val usuario1 = Usuario("Sebastian", "Castano", "3108238608",
				"sebas1929castano@gmail.com",LocalDate.parse("29-01-1996", formatter),"hola",
				"sebas123",2,2)

		val estado1 = Estado("ACTIVO")
		val estado2 = Estado("INACTIVO")

		estadoRepository!!.save(estado1)
		estadoRepository!!.save(estado2)

		val rol1 = Rol("ADMIN")
		val rol2 = Rol("USER")

		rolRepository!!.save(rol1)
		rolRepository!!.save(rol2)
		*/
	}
}


fun main(args: Array<String>) {
	runApplication<CulturApiApplication>(*args)
}
