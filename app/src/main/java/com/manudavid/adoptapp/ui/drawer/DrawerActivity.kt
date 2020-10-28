package com.manudavid.adoptapp.ui.drawer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.manudavid.adoptapp.R
import com.manudavid.adoptapp.ui.login.LoginActivity

class DrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        getSupportActionBar()?.hide();

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio, R.id.nav_adoptar, R.id.nav_darAdopcion, R.id.nav_hogarTemp, R.id.nav_voluntarios, R.id.nav_miPerfil

            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val exit = Intent(Intent.ACTION_MAIN)
            exit.addCategory(Intent.CATEGORY_HOME)
            startActivity(exit)
            finish()
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Presione de nuevo para salir", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.manuoverflow, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val correoRegistro = intent.getStringExtra("correo")
        val contrasenaRegistro = intent.getStringExtra("contrasena")

        return when (item.itemId) {

            R.id.cerrar_sesion -> {
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
}