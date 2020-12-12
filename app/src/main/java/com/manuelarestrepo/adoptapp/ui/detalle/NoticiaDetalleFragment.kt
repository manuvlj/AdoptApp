package com.manuelarestrepo.adoptapp.ui.detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.databinding.FragmentNoticiaDetalleBinding
import com.squareup.picasso.Picasso


class NoticiaDetalleFragment : Fragment() {

    private lateinit var binding: FragmentNoticiaDetalleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noticia_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNoticiaDetalleBinding.bind(view)
        val args: NoticiaDetalleFragmentArgs by navArgs()
        val noticiaDetalle = args.noticiaSeleccionada
        Picasso.get().load(noticiaDetalle.foto).into(binding.fotoNoticiaImageView)
        binding.tituloNoticiaDetalleTextView.text = noticiaDetalle.titulo
        binding.contenidoNoticiaTextView.text = noticiaDetalle.contenido
    }

}