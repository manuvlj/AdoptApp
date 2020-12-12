package com.manuelarestrepo.adoptapp.ui.miperfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Usuario
import kotlinx.android.synthetic.main.fragment_miperfil.*

class Miperfil : Fragment() {

    private var isSearching = true
    private var idUsuario: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_miperfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val passw = FirebaseAuth.getInstance()
        val auth = FirebaseAuth.getInstance().currentUser
        val uid = auth?.uid

        correo_perfil_editText.setText(auth?.email.toString())

        val database = FirebaseDatabase.getInstance()
        val myUsuarioRef = database.getReference("usuarios")

        if (isSearching) {
            val postListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data: DataSnapshot in snapshot.children) {
                        val usuario = data.getValue(Usuario::class.java)
                        if (usuario?.id.equals(uid)) {
                            nombre_perfil_editText.setText(usuario?.nombre)
                            apellidos_perfil_editText.setText(usuario?.apellido)
                            telefono_perfil_editText.setText(usuario?.telefono)
                            correo_perfil_editText.setText(usuario?.correo)
                            isSearching = false
                            idUsuario = usuario?.id
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
            myUsuarioRef.addValueEventListener(postListener)

        }


        actualizar_perfil_button.setOnClickListener {
            val childUpdates = HashMap<String, Any>()
            childUpdates["nombre"] = nombre_perfil_editText.text.toString()
            childUpdates["apellido"] = apellidos_perfil_editText.text.toString()
            childUpdates["telefono"] = telefono_perfil_editText.text.toString()
            idUsuario?.let { myUsuarioRef.child(it).updateChildren(childUpdates) }
            isSearching = true
            Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show()
        }
        actualizar_password_button.setOnClickListener {
            passw.signInWithEmailAndPassword(
                auth?.email.toString(),
                antigua_contrasena_editText.text.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (nueva_contrasena_editText.text.toString().isEmpty()) {
                            Toast.makeText(
                                context,
                                "Ingrese su nueva contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            auth?.updatePassword(nueva_contrasena_editText.text.toString())
                            Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_SHORT)
                                .show()
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "NO se pudo verificar su identidad, intente de nuevo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }


        //correo_text_view.text = auth?.email.toString()
    }
}