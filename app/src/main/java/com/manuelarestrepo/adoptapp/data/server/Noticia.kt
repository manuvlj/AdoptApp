package com.manuelarestrepo.adoptapp.data.server

import java.io.Serializable

class Noticia(
    val id: String? = "",
    val titulo: String = "",
    val descripcion: String = "",
    val contenido: String = "",
    val foto: String = ""
) : Serializable