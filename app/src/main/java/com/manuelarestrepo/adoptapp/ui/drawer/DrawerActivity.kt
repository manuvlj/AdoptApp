package com.manuelarestrepo.adoptapp.ui.drawer

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Usuario
import com.manuelarestrepo.adoptapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.nav_header_main.view.*

class DrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        supportActionBar?.hide()

        val datosRecibidos = intent.extras
        val correo = datosRecibidos?.getString("correo")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_inicio,
                R.id.nav_adoptar,
                R.id.nav_darAdopcion,
                R.id.nav_hogarTemp,
                R.id.nav_voluntarios,
                R.id.nav_miPerfil

            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val headerView = navView.getHeaderView(0)

        val database = FirebaseDatabase.getInstance()
        val myUsuarioRef = database.getReference("usuarios")

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {
                    val usuario = data.getValue(Usuario::class.java)
                    if (usuario?.correo.equals(correo)) {
                        headerView.nombre_header_text_view.text = usuario?.nombre
                        headerView.correo_header_text_view.text = usuario?.correo
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        myUsuarioRef.addValueEventListener(postListener)

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

        return when (item.itemId) {

            R.id.cerrar_sesion -> {
                val auth = FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
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