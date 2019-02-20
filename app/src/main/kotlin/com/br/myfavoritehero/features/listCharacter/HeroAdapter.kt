package com.br.myfavoritehero.features.listCharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroAdapter(
    private val elements: ArrayList<Hero>,
    private val listener: HeroEventListener
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        elements?.let {
            val hero = it[position]
            Picasso.get().load(hero.getThumbnailCardUrl()).into(holder.mLinearLayout.heroCardImage)
            holder.mLinearLayout.heroName.text = hero.name
            holder.mLinearLayout.setOnClickListener { listener.onHeroClicked(hero) }
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun updateUI(elements: ArrayList<Hero>){
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }


}