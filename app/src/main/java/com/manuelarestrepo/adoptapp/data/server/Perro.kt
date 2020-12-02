package com.manuelarestrepo.adoptapp.data.server

import java.io.Serializable

class Perro(
    val id: String? = "",
    val nombre: String = "",
    val edad: Long = 0,
    val sexo: String = "",
    val tamaño: String = "",
    val esterilizado: String = "",
    val vacunado: String = "",
    val caracteristicas: String = "",
    val saludable: String = "",
    val ciudad: String = "",
    val foto: String = ""
) : Serializable