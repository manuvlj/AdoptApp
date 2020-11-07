package com.manuelarestrepo.adoptapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.manuelarestrepo.adoptapp.AdoptApp
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.database.dao.UsuarioDAO
import com.manuelarestrepo.adoptapp.ui.drawer.DrawerActivity
import com.manuelarestrepo.adoptapp.ui.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        login_button.setOnClickListener {
            val correoLogin = email_text.text.toString()
            val contrasenaLogin = contrasenaRegistro_text.text.toString()
            val usuarioDAO: UsuarioDAO = AdoptApp.usuariodatabase.UsuarioDAO()
            val usuario = usuarioDAO.searchUsuario(correoLogin)

            if (usuario != null) {
                if (usuario.contrasena == contrasenaLogin) {
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DrawerActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Contrasena incorrecta", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } else {
                Toast.makeText(this, "Usuario no existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        registrarse_button.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        finish()
    }
}