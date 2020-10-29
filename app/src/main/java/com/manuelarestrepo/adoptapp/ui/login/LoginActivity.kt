package com.manuelarestrepo.adoptapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.ui.drawer.DrawerActivity
import com.manuelarestrepo.adoptapp.ui.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.hide()

        login_button.setOnClickListener {

            val correoRegistro = intent.getStringExtra("correo")
            val conRegistro = intent.getStringExtra("contrasena")
            val nombreRegistro = intent.getStringExtra("nombre")
            val correoLogin = email_text.text.toString()
            val contrasenaLogin = contrasenaRegistro_text.text.toString()


            if ((correoRegistro == correoLogin)&&(conRegistro == contrasenaLogin)){
                val intent = Intent(this, DrawerActivity::class.java)
                intent.putExtra("correo", correoLogin)
                intent.putExtra("contrasena", contrasenaLogin)
                intent.putExtra("nombre", nombreRegistro)
                startActivity(intent)
                //Prueba

            }

            else{
                mensajeError.setText(getString(R.string.error5))
            }

        }

        registrarse_button.setOnClickListener(){
            val intent = Intent( this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        finish()
    }
}