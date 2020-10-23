package com.manudavid.adoptapp.ui.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manudavid.adoptapp.ui.datepicker.DatePickerFragment
import com.manudavid.adoptapp.R
import com.manudavid.adoptapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    companion object{
        private const val EMPTY = ""
        private const val SPACE = " "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        getSupportActionBar()?.hide();



        /*val datosRecibidos =  intent.extras
        val numeroEnviado = datosRecibidos?.getInt("numero")
        Toast.makeText(this, "El número enviado es $numeroEnviado", Toast.LENGTH_SHORT).show()*/

        Log.d("Método", "onCreate")

        registro_button.setOnClickListener {
            val nombre= nombreRegistro_text.text.toString()
            val apellido = apellidoRegistro_text.text.toString()
            val correo = correoRegistro_text.text.toString()
            val telefono = telefonoRegistro_text.text.toString()
            val contrasena = contrasenaRegistro_text.text.toString()
            val repContrasena = repContrasena_text.text.toString()
            val ciudad = ciudad_spinner.selectedItem
            val ocupacion = ocupacion_spinner.selectedItem
            val noticias = noticias_checkBox.isChecked.toString()


            if(nombre == EMPTY || correo == EMPTY || contrasena == EMPTY || repContrasena == EMPTY || telefono == EMPTY || ciudad == EMPTY || ocupacion == EMPTY){
                textView.text =getString(R.string.error1)
            }

            else{
                if (contrasena != repContrasena)
                    textView.text = getString(R.string.error2)

                else if(contrasena.length < 6)
                    textView.text =getString(R.string.error3)

                else if(!condiciones_checkBox.isChecked)
                        textView.text = getString(R.string.error4)

                else {

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("correo", correo)
                    intent.putExtra("contrasena", contrasena)
                    intent.putExtra("nombre", nombre)
                    startActivity(intent)
                    finish()


                }
            }

        }

        fechaNacimiento_button.setOnClickListener{
            // Initialize a new DatePickerFragment
            val newFragment =
                DatePickerFragment()
            // Show the date picker dialog
            newFragment.show(fragmentManager, "Date Picker")
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}