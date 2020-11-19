package com.manuelarestrepo.adoptapp.ui.voluntarios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Voluntario
import com.manuelarestrepo.adoptapp.ui.drawer.DrawerActivity
import kotlinx.android.synthetic.main.activity_formulario_voluntarios.*

class FormularioVoluntariosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_voluntarios)

        enviar_formulario_button.setOnClickListener {
            val nombre = nombre_voluntario_edit_text.text.toString()
            val apellido = apellido_voluntarios_edit_text.text.toString()
            val telefono = telefono_voluntarios_edit_text.text.toString()
            val correo = correo_voluntarios_edit_text.text.toString()
            val direccion = direccion_voluntarios_edit_text.text.toString()
            val ciudad = ciudad_voluntarios_spinner.selectedItem.toString()

            crearVoluntarioEnFirebase(nombre, apellido, telefono, correo, direccion, ciudad)
        }
    }

    private fun crearVoluntarioEnFirebase(
        nombre: String,
        apellido: String,
        telefono: String,
        correo: String,
        direccion: String,
        ciudad: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val myVoluntariosRef = database.getReference("voluntarios")
        val id = myVoluntariosRef.push().key
        val voluntario = Voluntario(id, nombre, apellido, telefono, correo, direccion, ciudad)
        id?.let {
            myVoluntariosRef.child(it).setValue(voluntario)
        }
        Toast.makeText(
            baseContext, "Voluntario registrado",
            Toast.LENGTH_SHORT
        ).show()
        goToDraweActivity()
    }

    private fun goToDraweActivity() {
        val intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
        finish()
    }
}