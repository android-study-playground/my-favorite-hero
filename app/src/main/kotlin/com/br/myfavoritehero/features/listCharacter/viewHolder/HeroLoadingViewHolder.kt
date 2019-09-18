package com.br.myfavoritehero.features.listCharacter.viewHolder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.features.listCharacter.HeroLoadingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class HeroLoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var mShimmerFrameLayout: ShimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)
    var mLoadingHeroRecyclerView: RecyclerView = view.findViewById(R.id.loadingHeroRecyclerView)

    fun bind(){
        mShimmerFrameLayout.startShimmer()
        mShimmerFrameLayout.context?.let {
            mLoadingHeroRecyclerView.layoutManager = LinearLayoutManager(it)
        }
        mLoadingHeroRecyclerView.adapter = HeroLoadingAdapter()
    }
}