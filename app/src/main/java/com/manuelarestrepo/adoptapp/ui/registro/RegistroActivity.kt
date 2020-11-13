package com.manuelarestrepo.adoptapp.ui.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.ui.datepicker.DatePickerFragment
import com.manuelarestrepo.adoptapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    companion object {
        private const val EMPTY = ""
        private const val SPACE = " "
        private val TAG = RegistroActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        registro_button.setOnClickListener {
            val nombre= nombre_header.text.toString()
            val apellido = apellidoRegistro_text.text.toString()
            val correo = correo_header.text.toString()
            val telefono = telefonoRegistro_text.text.toString()
            val contrasena = contrasenaRegistro_text.text.toString()
            val repContrasena = repContrasena_text.text.toString()
            val ciudad = ciudad_spinner.selectedItem.toString()
            val ocupacion = ocupacion_spinner.selectedItem
            val noticias = noticias_checkBox.isChecked.toString()


            if (nombre == EMPTY || apellido == EMPTY || correo == EMPTY || contrasena == EMPTY || repContrasena == EMPTY || telefono == EMPTY || ciudad == EMPTY || ocupacion == EMPTY) {
                darAdopcion_textView.text = getString(R.string.error1)
            } else {
                if (contrasena != repContrasena)
                    darAdopcion_textView.text = getString(R.string.error2)
                else if (contrasena.length < 6)
                    darAdopcion_textView.text = getString(R.string.error3)

                else if(!condiciones_checkBox.isChecked)
                        darAdopcion_textView.text = getString(R.string.error4)

                else {
                    registroEnFirebase(correo, contrasena)
                }
            }


        }

        fechaNacimiento_button.setOnClickListener {
            // Initialize a new DatePickerFragment
            val newFragment =
                DatePickerFragment()
            // Show the date picker dialog
            newFragment.show(fragmentManager, "Date Picker")
        }

    }

    private fun registroEnFirebase(correo: String, contrasena: String) {
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}