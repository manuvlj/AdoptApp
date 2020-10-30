package com.manuelarestrepo.adoptapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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