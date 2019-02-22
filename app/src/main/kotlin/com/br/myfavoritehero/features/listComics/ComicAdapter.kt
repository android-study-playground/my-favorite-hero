package com.br.myfavoritehero.features.listComics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.ComicEventListener
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.util.getLargePortraitThumbnail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_item.view.*

class ComicAdapter(
        private val elements: ArrayList<Comic>,
        private val listener: ComicEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ComicViewHolder){
            elements.let {
                val comic = it[position]
                Picasso.get().load(comic.thumbnail.path.getLargePortraitThumbnail()).into(holder.mLinearLayout.comicImage)
                holder.mLinearLayout.comicTitle.text = comic.title
                holder.mLinearLayout.comicDescription.text = comic.description
                holder.mLinearLayout.setOnClickListener { listener.onComicClicked(comic) }
            }
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun updateUI(elements: ArrayList<Comic>){
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }

}