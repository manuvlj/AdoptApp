package com.manudavid.adoptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.hide();

        login_button.setOnClickListener {

            val datosRegistro = intent.extras
            val correoRegistro = intent.getStringExtra("correo")
            val conRegistro = intent.getStringExtra("contrasena")
            val correoLogin = email_text.text.toString()
            val contrasenaLogin = contrasenaRegistro_text.text.toString()


            if ((correoRegistro == correoLogin)){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("correo", correoLogin)
                startActivity(intent)
                finish()
            }
            else{
                mensajeError.setText("$correoRegistro")
            }

        }

        registrarse_button.setOnClickListener(){
            val intent = Intent( this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}