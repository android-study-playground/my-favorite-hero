package com.br.myfavoritehero.features.listCharacter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.SharedPreferencesHelper
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_item.view.favoriteIcon
import kotlinx.android.synthetic.main.hero_item.view.heroName
import kotlinx.android.synthetic.main.hero_item.view.heroCardImage

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
            val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
            context = view.context
            HeroViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_list_loading, parent, false)
            context = view.context
            HeroLoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var favorited: Boolean = false
        if (holder is HeroViewHolder) {
            val hero = elements[position]
            Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail()).into(holder.mLinearLayout.heroCardImage)
            holder.mLinearLayout.heroName.text = hero.name
            holder.mLinearLayout.setOnClickListener { listener.onHeroClicked(hero) }
            context?.let {
                favorited = SharedPreferencesHelper.isFavorited(it, hero.id)
                switchVisibility(favorited, holder)
            }

            holder.mLinearLayout.favoriteIcon.setOnClickListener {
                favorited = if (favorited) {
                    SharedPreferencesHelper.setFavorite(it.context, hero.id, false)
                    Snackbar.make(holder.mLinearLayout, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
                    !favorited
                } else {
                    holder.mLinearLayout.favoriteIcon.setImageResource(R.drawable.un_favorite)
                    SharedPreferencesHelper.setFavorite(it.context, hero.id)
                    Snackbar.make(holder.mLinearLayout, R.string.favorited, Snackbar.LENGTH_SHORT).show()
                    !favorited
                }
                switchVisibility(favorited, holder)
            }
        } else if (holder is HeroLoadingViewHolder) {
            holder.mShimmerFrameLayout.startShimmer()
            context?.let {
                holder.mLoadingHeroRecyclerView.layoutManager = LinearLayoutManager(it)
            }
            holder.mLoadingHeroRecyclerView.adapter = HeroLoadingAdapter()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= elements.size) LOADING_TYPE else HERO_TYPE
    }

    override fun getItemCount(): Int {
        return if (isLoading) elements.size + 1 else elements.size
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
        this.elements.addAll(elements)
        this.notifyDataSetChanged()
    }

    private fun switchVisibility(favorited: Boolean, holder: HeroViewHolder) {
        if (favorited) {
            holder.mLinearLayout.favoriteIcon.setImageResource(R.drawable.un_favorite)
        } else {
            holder.mLinearLayout.favoriteIcon.setImageResource(R.drawable.favorite)
        }
    }
}