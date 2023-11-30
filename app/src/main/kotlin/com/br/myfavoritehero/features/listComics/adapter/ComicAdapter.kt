package com.br.myfavoritehero.features.listComics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.interfaces.ComicEventListener
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.databinding.ComicItemBinding
import com.br.myfavoritehero.features.listComics.viewHolder.ComicViewHolder

class ComicAdapter(
    private val elements: ArrayList<Comic>,
    private val listener: ComicEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ComicViewHolder) {
            elements.let {
                val comic = it[position]
                holder.bind(comic, listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun updateUI(elements: ArrayList<Comic>) {
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }
}