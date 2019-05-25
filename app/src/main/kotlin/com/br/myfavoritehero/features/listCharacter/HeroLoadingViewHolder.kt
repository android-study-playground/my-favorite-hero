package com.br.myfavoritehero.features.listCharacter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.facebook.shimmer.ShimmerFrameLayout

class HeroLoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var mShimmerFrameLayout: ShimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)
    var mLoadingHeroRecyclerView: RecyclerView = view.findViewById(R.id.loadingHeroRecyclerView)
}