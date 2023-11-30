package com.br.myfavoritehero.features.listCharacter.viewHolder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.databinding.HeroListLoadingBinding
import com.br.myfavoritehero.features.listCharacter.HeroLoadingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class HeroLoadingViewHolder(val view: HeroListLoadingBinding) : RecyclerView.ViewHolder(view.root) {
    private var mShimmerFrameLayout: ShimmerFrameLayout = view.root.findViewById(R.id.shimmer_view_container)
    private var mLoadingHeroRecyclerView: RecyclerView = view.root.findViewById(R.id.loadingHeroRecyclerView)

    fun bind(){
        mShimmerFrameLayout.startShimmer()
        mShimmerFrameLayout.context?.let {
            mLoadingHeroRecyclerView.layoutManager = LinearLayoutManager(it)
        }
        mLoadingHeroRecyclerView.adapter = HeroLoadingAdapter()
    }
}