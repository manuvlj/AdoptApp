package com.manuelarestrepo.adoptapp.ui.adoptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Perro
import com.manuelarestrepo.adoptapp.databinding.AdoptarItemBinding
import com.squareup.picasso.Picasso

class AdoptarRVAdapter(
    var perrosList: ArrayList<Perro>,
    val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AdoptarRVAdapter.AdoptarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdoptarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.adoptar_item, parent, false)
        return AdoptarViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: AdoptarViewHolder, position: Int) {
        val perro = perrosList[position]
        holder.bindPerro(perro)
    }

    override fun getItemCount(): Int {
        return perrosList.size
    }

    class AdoptarViewHolder(
        itemView: View,
        var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = AdoptarItemBinding.bind(itemView)
        fun bindPerro(perro: Perro) {
            Picasso.get().load(perro.foto).into(binding.fotoImageView)
            binding.nombreTextView.text = perro.nombre
            binding.sexoTextView.text = "Sexo: ".plus(perro.sexo)
            binding.edadTextView.text = "Edad: ".plus(perro.edad.toString())
            binding.tamanoTextView.text = "Tamaño: ".plus(perro.tamaño)

            binding.itemCardView.setOnClickListener {
                onItemClickListener.onItemClick(perro)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(perro: Perro)
    }
}