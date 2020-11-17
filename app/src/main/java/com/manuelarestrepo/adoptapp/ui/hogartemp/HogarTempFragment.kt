package com.manuelarestrepo.adoptapp.ui.hogartemp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.HogarTemporal
import com.manuelarestrepo.adoptapp.databinding.FragmentHogarTempBinding

class HogarTempFragment : Fragment() {

    private lateinit var binding: FragmentHogarTempBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHogarTempBinding.bind(view)

        binding.enviarHogarButton.setOnClickListener {

            val nombreHogar = binding.nombreHogarEditText.text.toString()
            val apellidoHogar = binding.apellidosHogarEditText.text.toString()
            val telefonoHogar = binding.telefonoHogarEditText.text.toString().toInt()
            val correoHogar = binding.correoHogarEditText.text.toString()
            val ciudadHogar = binding.ciudadHogarSpinner.selectedItem.toString()
            val direccionHogar = binding.direccionHogarEditText.text.toString()
            val numPerrosHogar = binding.cantidadPerrosEditText.text.toString().toInt()
            val experienciaHogar =
                if (binding.siHogarRadioButton.isChecked) getString(R.string.si) else getString(R.string.no)

            crearHogarTemporal(
                nombreHogar,
                apellidoHogar,
                telefonoHogar,
                correoHogar,
                ciudadHogar,
                direccionHogar,
                numPerrosHogar,
                experienciaHogar
            )
            Toast.makeText(
                activity,
                "Formulario enviado con éxito. ¡Gracias por ayudar!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun crearHogarTemporal(
        nombreHogar: String,
        apellidoHogar: String,
        telefonoHogar: Int,
        correoHogar: String,
        ciudadHogar: String,
        direccionHogar: String,
        numPerrosHogar: Int,
        experienciaHogar: String
    ) {
        val database = FirebaseDatabase.getInstance()
        val hogarRef = database.getReference("hogartemporal")

        val id = hogarRef.push().key
        val voluntarioHogar = HogarTemporal(
            id,
            nombreHogar,
            apellidoHogar,
            telefonoHogar,
            correoHogar,
            ciudadHogar,
            direccionHogar,
            numPerrosHogar,
            experienciaHogar
        )
        id?.let { hogarRef.child(id).setValue(voluntarioHogar) }
        cleanViews()
    }

    private fun cleanViews() {
        binding.nombreHogarEditText.setText("")
        binding.apellidosHogarEditText.setText("")
        binding.telefonoHogarEditText.setText("")
        binding.correoHogarEditText.setText("")
        binding.direccionHogarEditText.setText("")
        binding.cantidadPerrosEditText.setText("")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hogar_temp, container, false)
    }

}