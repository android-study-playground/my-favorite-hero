package com.br.myfavoritehero.features.listCharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.models.Hero
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroAdapter(
    private val elements: ArrayList<Hero>,
    private val layout: Int,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        elements?.let {
            holder.mLinearLayout.heroName.text = it[position].name
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }


}