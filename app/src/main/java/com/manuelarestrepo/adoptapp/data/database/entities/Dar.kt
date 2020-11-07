package com.manuelarestrepo.adoptapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_darAdopcion")
data class Dar(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nombre") val nombre: String? = null,
    @ColumnInfo(name = "apellido") val apellido: String? = null,
    @ColumnInfo(name = "telefono") val telefono: String? = null,
    @ColumnInfo(name = "correo") val correo: String? = null,
    @ColumnInfo(name = "ciudadNacimiento") val ciudadNacimiento: String? = null,
    @ColumnInfo(name = "fechaNacimiento") val fechaNacimiento: String? = null,
    @ColumnInfo(name = "contrasena") val contrasena: String? = null
)