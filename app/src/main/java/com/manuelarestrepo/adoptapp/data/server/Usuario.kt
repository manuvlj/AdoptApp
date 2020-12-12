package com.manuelarestrepo.adoptapp.data.server

data class Usuario(
    val id: String? = "",
    val nombre: String? = "",
    val apellido: String? = "",
    val telefono: String? = "",
    val correo: String? = "",
    val ciudad: String? = "",
    val ocupacion: String? = ""
)