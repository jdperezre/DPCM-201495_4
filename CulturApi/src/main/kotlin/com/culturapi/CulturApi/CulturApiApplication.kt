package com.culturapi.CulturApi

import com.culturapi.CulturApi.dao.EventoRepository
import com.culturapi.CulturApi.model.Evento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class CulturApiApplication:CommandLineRunner{

	@Autowired
	val eventoRepository: EventoRepository? = null

	override fun run(vararg args: String?) {

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val evento = Evento("Rock al parque", "Eventos S.A.S",
				"Evento cultural y ludico","3100000",
				"Km 15 via el Roble", LocalDate.parse("25-11-2020", formatter),
				LocalDate.parse("27-11-2020", formatter),"img.jpg","1",2)


		eventoRepository!!.save(evento)

	}
}


fun main(args: Array<String>) {
	runApplication<CulturApiApplication>(*args)
}
