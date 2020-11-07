package com.manuelarestrepo.adoptapp

import android.app.Application
import androidx.room.Room
import com.manuelarestrepo.adoptapp.data.database.UsuarioDatabase

class AdoptApp : Application() {
    companion object {
        lateinit var usuariodatabase: UsuarioDatabase
    }

    override fun onCreate() {
        super.onCreate()

        usuariodatabase = Room.databaseBuilder(
            this,
            UsuarioDatabase::class.java,
            "usuario_DB"
        ).allowMainThreadQueries()
            .build()
    }
}