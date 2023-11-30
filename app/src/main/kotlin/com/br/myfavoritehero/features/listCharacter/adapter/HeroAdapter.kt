package com.br.myfavoritehero.features.listCharacter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.databinding.HeroItemBinding
import com.br.myfavoritehero.databinding.HeroListLoadingBinding
import com.br.myfavoritehero.features.listCharacter.viewHolder.HeroLoadingViewHolder
import com.br.myfavoritehero.features.listCharacter.viewHolder.HeroViewHolder

class HeroAdapter(
    private val elements: ArrayList<Hero>,
    private val listener: HeroEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HERO_TYPE = 0
        private const val LOADING_TYPE = 1
    }

    private var isLoading = false
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HERO_TYPE) {
            val view =
                HeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            context = view.root.context
            HeroViewHolder(view)
        } else {
            val view = HeroListLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            context = view.root.context
            HeroLoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeroViewHolder) {
            val hero = elements[position]
            holder.bind(hero, listener)
        } else if (holder is HeroLoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= elements.size) LOADING_TYPE else HERO_TYPE
    }

    override fun getItemCount(): Int {
        return if (isLoading) elements.size + 2 else elements.size
    }

    fun startLoading() {
        isLoading = true
        notifyDataSetChanged()
    }

    fun stopLoading() {
        isLoading = false
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean {
        return isLoading
    }

    fun updateUI(elements: ArrayList<Hero>) {
        this.elements.addAll(elements.filter{ !this.elements.contains(it) })
        this.notifyDataSetChanged()
    }

    fun updateHero(hero: Hero) {
        val index = this.elements.indexOfFirst { it == hero }
        if (index > 0) this.elements[index].isFavorite = !this.elements[index].isFavorite
        this.notifyDataSetChanged()
    }

}