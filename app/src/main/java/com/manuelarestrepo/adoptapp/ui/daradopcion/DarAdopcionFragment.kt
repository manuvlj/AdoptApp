package com.manuelarestrepo.adoptapp.ui.daradopcion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.manuelarestrepo.adoptapp.R

class DarAdopcionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dar_adopcion, container, false)
    }

    companion object
}