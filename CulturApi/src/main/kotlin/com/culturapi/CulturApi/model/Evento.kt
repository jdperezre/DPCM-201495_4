package com.culturapi.CulturApi.model


import com.cloudinary.StoredFile
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*
import java.util.Date;


@Entity
@Table(name ="eventos")
data class Evento(

        val titulo: String = "" ,
        val organizacion: String = "",
        val descripcion: String = "",
        val telefono: String = "",
        val direccion: String = "",
        val fecha_inicio: LocalDate,
        val fecha_final: LocalDate,
        val hora :LocalTime,
        val imagen: String = "",
        val idCategoria: String = "",
        val idEstado: String= ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0
}

@Entity(name = "photos")
class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Basic
    var title: String? = null

    @Basic
    var image: String? = null

    @Basic
    var createdAt: Date = Date()

    var upload: StoredFile
        get() {
            val file = StoredFile()
            file.preloadedFile = image
            return file
        }
        set(file) {
            image = file.preloadedFile
        }

}
