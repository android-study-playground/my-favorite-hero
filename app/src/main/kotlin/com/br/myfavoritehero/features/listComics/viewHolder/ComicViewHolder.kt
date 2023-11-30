package com.br.myfavoritehero.features.listComics.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.interfaces.ComicEventListener
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.databinding.ComicItemBinding
import com.br.myfavoritehero.util.getLargePortraitThumbnail
import com.squareup.picasso.Picasso

class ComicViewHolder(private val binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(comic: Comic, listener: ComicEventListener) {
        Picasso.get().load(comic.thumbnail.path.getLargePortraitThumbnail())
            .into(binding.comicImage)
        binding.comicTitle.text = comic.title
        binding.comicDescription.text = comic.description
        binding.root.setOnClickListener { listener.onComicClicked(comic) }
    }
}