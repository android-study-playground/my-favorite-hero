package com.br.myfavoritehero.features.listCharacter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.databinding.HeroItemBinding
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.squareup.picasso.Picasso

class HeroViewHolder(private val binding: HeroItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hero: Hero, listener: HeroEventListener) {
        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail())
            .into(binding.heroCardImage)
        binding.heroName.text = hero.name
        binding.root.setOnClickListener { listener.onHeroClicked(hero) }
        if (hero.isFavorite) binding.favoriteIcon.setImageResource(R.drawable.un_favorite)
        binding.favoriteIcon.setOnClickListener {
            listener.onHeroFavorited(hero)
        }

    }
}