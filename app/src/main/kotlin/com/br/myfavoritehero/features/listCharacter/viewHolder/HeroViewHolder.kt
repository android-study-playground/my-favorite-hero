package com.br.myfavoritehero.features.listCharacter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroViewHolder(val mLinearLayout: View) : RecyclerView.ViewHolder(mLinearLayout) {

    fun bind(hero: Hero, listener: HeroEventListener) {
        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail())
            .into(mLinearLayout.heroCardImage)
        mLinearLayout.heroName.text = hero.name
        mLinearLayout.setOnClickListener { listener.onHeroClicked(hero) }
        if (hero.isFavorite) mLinearLayout.favoriteIcon.setImageResource(R.drawable.un_favorite)

        mLinearLayout.favoriteIcon.setOnClickListener {
            listener.onHeroFavorited(hero)
        }

    }
}