package com.br.myfavoritehero.features.listCharacter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.databinding.HeroItemBinding
import com.br.myfavoritehero.features.listCharacter.viewHolder.HeroViewHolder

class FavoriteHeroAdapter(
    private val elements: ArrayList<Hero>,
    private val listener: HeroEventListener
) : RecyclerView.Adapter<HeroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = HeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        elements.let {
            val hero = it[position]
            holder.bind(hero, listener)
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    fun updateUI(elements: ArrayList<Hero>) {
        this.elements.clear()
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }

}