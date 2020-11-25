package com.manuelarestrepo.adoptapp.ui.voluntarios

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.ui.mapas.MapsActivity
import kotlinx.android.synthetic.main.fragment_voluntarios.*

class VoluntariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voluntarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inscribete_button.setOnClickListener {
            val intent = Intent(context, FormularioVoluntariosActivity::class.java)
            startActivity(intent)
        }

        mapa_button.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            startActivity(intent)
        }
    }

}