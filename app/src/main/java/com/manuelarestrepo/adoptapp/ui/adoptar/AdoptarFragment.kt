package com.manuelarestrepo.adoptapp.ui.adoptar

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
import com.manuelarestrepo.adoptapp.data.server.Perro
import com.manuelarestrepo.adoptapp.databinding.FragmentAdoptarBinding

//import androidx.navigation.fragment.findNavController

class AdoptarFragment : Fragment(), AdoptarRVAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAdoptarBinding
    var listPerros: MutableList<Perro> = mutableListOf()
    private lateinit var adoptarRVAdapter: AdoptarRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoptar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAdoptarBinding.bind(view)

        binding.adoptarRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.adoptarRecyclerView.setHasFixedSize(true)


        adoptarRVAdapter = AdoptarRVAdapter(listPerros as ArrayList<Perro>, this@AdoptarFragment)

        binding.adoptarRecyclerView.adapter = adoptarRVAdapter

        cargarDesdeFirebase()

        adoptarRVAdapter.notifyDataSetChanged()

    }

    fun cargarDesdeFirebase() {
        val database = FirebaseDatabase.getInstance()
        val myPerrosRef = database.getReference("adoptar")

        listPerros.clear()


        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.children) {
                    val perro = dato.getValue(Perro::class.java)
                    perro?.let { listPerros.add(it) }
                }
                adoptarRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        myPerrosRef.addValueEventListener(postListener)

    }

    override fun onItemClick(perro: Perro) {
        val action = AdoptarFragmentDirections.actionNavAdoptarToDetalleFragment(perro)
        findNavController().navigate(action)
    }
}