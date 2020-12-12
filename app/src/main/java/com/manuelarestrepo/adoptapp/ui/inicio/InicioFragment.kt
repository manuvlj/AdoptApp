package com.manuelarestrepo.adoptapp.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Noticia
import com.manuelarestrepo.adoptapp.databinding.FragmentInicioBinding

class InicioFragment : Fragment(), NoticiasAdapter.OnItemCLickListener {

    private lateinit var binding: FragmentInicioBinding
    var listNoticias: MutableList<Noticia> = mutableListOf()
    private lateinit var noticiasAdapter: NoticiasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentInicioBinding.bind(view)

        binding.noticiasRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.noticiasRecyclerView.setHasFixedSize(true)

        noticiasAdapter = NoticiasAdapter(listNoticias as ArrayList<Noticia>, this@InicioFragment)

        binding.noticiasRecyclerView.adapter = noticiasAdapter

        cargarDesdeFirebase()

        noticiasAdapter.notifyDataSetChanged()
    }

    private fun cargarDesdeFirebase() {
        val database = FirebaseDatabase.getInstance()
        val myNoticiasRef = database.getReference("noticias")

        listNoticias.clear()

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.children) {
                    val noticia = dato.getValue(Noticia::class.java)
                    noticia?.let { listNoticias.add(it) }
                }
                noticiasAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        myNoticiasRef.addValueEventListener(postListener)
    }

    override fun onItemClick(noticia: Noticia) {
        val action = InicioFragmentDirections.actionNavInicioToNoticiaDetalleFragment(noticia)
        findNavController().navigate(action)
    }

}