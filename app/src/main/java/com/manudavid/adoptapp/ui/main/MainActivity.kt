package com.manudavid.adoptapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.manudavid.adoptapp.R
import com.manudavid.adoptapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val correoRegistro = intent.getStringExtra("correo")
        //val contrasenaRegistro = intent.getStringExtra("contrasena")

        correoMain_text.setText("Su correo es: $correoRegistro")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val correoRegistro = intent.getStringExtra("correo")
        val contrasenaRegistro = intent.getStringExtra("contrasena")

        return when (item.itemId) {

            R.id.nav_cerrarSesion -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("correo", correoRegistro)
                    intent.putExtra("contrasena", contrasenaRegistro)
                    startActivity(intent)
                    finish()

                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_LONG).show()
                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onBackPressed() {
        val correoRegistro = intent.getStringExtra("correo")
        val contrasenaRegistro = intent.getStringExtra("contrasena")
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("correo", correoRegistro)
        intent.putExtra("contrasena", contrasenaRegistro)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Método", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Método", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Método", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Método", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Método", "OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Método", "OnRestart")
    }
}