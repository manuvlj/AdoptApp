package com.manuelarestrepo.adoptapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manuelarestrepo.adoptapp.data.database.dao.UsuarioDAO
import com.manuelarestrepo.adoptapp.data.database.entities.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioDatabase : RoomDatabase() {
    abstract fun UsuarioDAO(): UsuarioDAO
}