package com.manuelarestrepo.adoptapp.ui.daradopcion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.DarEnAdopcion
import com.manuelarestrepo.adoptapp.databinding.FragmentDarAdopcionBinding

class DarAdopcionFragment : Fragment() {

    private lateinit var binding: FragmentDarAdopcionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dar_adopcion, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDarAdopcionBinding.bind(view)

        binding.enviarDarButton.setOnClickListener {

            //Datos del propietario actual
            val nombrePropietario = binding.nombreDarEditText.text.toString()
            val apellidoPropietario = binding.apellidoDarEditText.text.toString()
            val correoPropietario = binding.correoDarEditText.text.toString()

            //Datos del perro que se quiere dar en adopcion
            val nombrePerroDar = binding.nombrePerroDarEditText.text.toString()
            val edadPerroDar = binding.edadPerroDarEditText.text.toString().toInt()
            val tamanoPerroDar = binding.tamanoSpinner.selectedItem.toString()
            val sexoPerroDar =
                if (binding.hembraRadioButton.isChecked) getString(R.string.hembra) else getString(R.string.macho)
            val esterilizadoPerroDar =
                if (binding.siEsteriliRadioButton.isChecked) getString(R.string.si) else getString(R.string.no)
            val vacunasPerroDar =
                if (binding.siVacunasRadioButton.isChecked) getString(R.string.si) else getString(R.string.no)
            val justificacion = binding.justificacionEditText.text.toString()

            crearDarEnAdopcion(
                nombrePropietario,
                apellidoPropietario,
                correoPropietario,
                nombrePerroDar,
                edadPerroDar,
                tamanoPerroDar,
                sexoPerroDar,
                esterilizadoPerroDar,
                vacunasPerroDar,
                justificacion
            )
            Toast.makeText(activity, "Formulario enviado con éxito", Toast.LENGTH_SHORT).show()
        }
    }

    private fun crearDarEnAdopcion(
        nombrePropietario: String,
        apellidoPropietario: String,
        correoPropietario: String,
        nombrePerroDar: String,
        edadPerroDar: Int,
        tamanoPerroDar: String,
        sexoPerroDar: String,
        esterilizadoPerroDar: String,
        vacunasPerroDar: String,
        justificacion: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val darRef = database.getReference("darenadopcion")

        val id = darRef.push().key
        val candidato = DarEnAdopcion(
            id,
            nombrePropietario,
            apellidoPropietario,
            correoPropietario,
            nombrePerroDar,
            edadPerroDar,
            tamanoPerroDar,
            sexoPerroDar,
            esterilizadoPerroDar,
            vacunasPerroDar,
            justificacion
        )
        id?.let { darRef.child(it).setValue(candidato) }
        cleanView()

    }

    private fun cleanView() {
        binding.nombreDarEditText.setText("")
        binding.apellidoDarEditText.setText("")
        binding.correoDarEditText.setText("")
        binding.nombrePerroDarEditText.setText("")
        binding.edadPerroDarEditText.setText("")
        binding.justificacionEditText.setText("")
    }
}