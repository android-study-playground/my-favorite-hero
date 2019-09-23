package com.br.myfavoritehero.features.listCharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R

class HeroLoadingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var elements: ArrayList<String> = arrayListOf("", "")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hero_item_placeholder, parent, false)
        return HeroLoadingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    class HeroLoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}