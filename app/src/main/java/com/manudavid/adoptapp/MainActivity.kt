package com.manudavid.adoptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.cerrar_sesion -> {
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_LONG).show()
                true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
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