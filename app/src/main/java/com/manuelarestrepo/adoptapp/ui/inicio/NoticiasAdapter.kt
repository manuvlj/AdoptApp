package com.manuelarestrepo.adoptapp.ui.inicio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Noticia
import com.manuelarestrepo.adoptapp.databinding.NoticiaListItemBinding
import com.squareup.picasso.Picasso

class NoticiasAdapter(
    var noticiasList: ArrayList<Noticia>,
    val onItemClickListener: OnItemCLickListener
) : RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.noticia_list_item, parent, false)
        return NoticiasViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return noticiasList.size
    }


    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        val noticia = noticiasList[position]
        holder.bindNoticia(noticia)
    }

    class NoticiasViewHolder(
        itemView: View,
        var onItemCLickListener: OnItemCLickListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = NoticiaListItemBinding.bind(itemView)
        fun bindNoticia(noticia: Noticia) {
            Picasso.get().load(noticia.foto).into(binding.noticiaImageView)
            binding.tituloNoticiaTextView.text = noticia.titulo
            binding.descripcionNoticiaTextView.text = noticia.descripcion

            binding.noticiaItemCardView.setOnClickListener {
                onItemCLickListener.onItemClick(noticia)
            }
        }
    }

    interface OnItemCLickListener {
        fun onItemClick(noticia: Noticia)
    }


}