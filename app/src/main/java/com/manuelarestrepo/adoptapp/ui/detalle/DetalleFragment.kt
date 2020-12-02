package com.manuelarestrepo.adoptapp.ui.detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.databinding.FragmentDetalleBinding
import com.squareup.picasso.Picasso

class DetalleFragment : Fragment() {

    private lateinit var binding: FragmentDetalleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetalleBinding.bind(view)
        val args: DetalleFragmentArgs by navArgs()
        val perroDetalle = args.perroSeleccionado
        Picasso.get().load(perroDetalle.foto).into(binding.fotoDetalleImageView)
        binding.nombreDetalleTextView.text = perroDetalle.nombre
        binding.sexoDetalleTextView.text = "Sexo: ".plus(perroDetalle.sexo)
        binding.edadDetalleTextView.text = "Edad: ".plus(perroDetalle.edad.toString())
        binding.ciudadDetalleTextView.text = "Ciudad: ".plus(perroDetalle.ciudad)
        binding.esterilizadoDetalleTextView.text = "Esterilizado: ".plus(perroDetalle.esterilizado)
        binding.caracteristicasDetalleTextView.text =
            "Caracteristicas: ".plus(perroDetalle.caracteristicas)
        binding.saludableDetalleTextView.text = "Saludable: ".plus(perroDetalle.saludable)
        binding.tamanoDetalleTextView.text = "Tamaño: ".plus(perroDetalle.tamaño)
        binding.vacunadoDetalleTextView.text = "Vacunado: ".plus(perroDetalle.vacunado)


    }

}