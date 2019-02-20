package com.br.myfavoritehero.features.listCharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_item.view.*
import kotlinx.android.synthetic.main.hero_list_loading.view.*

class HeroAdapter(
    private val elements: ArrayList<Hero>,
    private val listener: HeroEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HERO_TYPE = 0
        private const val LOADING_TYPE = 1
    }

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HERO_TYPE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
            HeroViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_list_loading, parent, false)
            HeroLoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeroViewHolder){
            elements?.let {
                val hero = it[position]
                Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail()).into(holder.mLinearLayout.heroCardImage)
                holder.mLinearLayout.heroName.text = hero.name
                holder.mLinearLayout.setOnClickListener { listener.onHeroClicked(hero) }
            }
        }else if (holder is HeroLoadingViewHolder){
            holder.mLinearLayoutLoading.shimmer_view_container.startShimmerAnimation()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= elements.size) LOADING_TYPE else HERO_TYPE
    }

    override fun getItemCount(): Int {
        return if (isLoading) elements.size + 1 else elements.size
    }

    fun startLoading(){
        isLoading = true
        notifyDataSetChanged()
    }

    fun stopLoading(){
        isLoading = false
        notifyDataSetChanged()
    }

    fun updateUI(elements: ArrayList<Hero>){
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }

}