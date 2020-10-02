package com.manudavid.adoptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        getSupportActionBar()?.hide();

        var nombre= nombreRegistro_text.text.toString()
        var apellido = apellidoRegistro_text.text.toString()
        var correo = correoRegistro_text.text.toString()
        var telefono = telefonoRegistro_text.text.toString()
        var contrasena = contrasena_text.text.toString()
        var repContrasena = repContrasena_text.text.toString()

        val datosRecibidos =  intent.extras
        val numeroEnviado = datosRecibidos?.getInt("numero")
        Toast.makeText(this, "El número enviado es $numeroEnviado", Toast.LENGTH_SHORT).show()

        Log.d("Método", "onCreate")

        registro_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("correo", correo)
            intent.putExtra("contraseña", contrasena)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}