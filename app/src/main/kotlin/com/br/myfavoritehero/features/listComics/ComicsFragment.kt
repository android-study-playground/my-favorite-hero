package com.br.myfavoritehero.features.listComics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.br.myfavoritehero.R

class ComicsFragment : Fragment() {

    companion object {
        private const val HERO_ID = "HERO_ID"

        fun newInstance(heroId:Int) : ComicsFragment{
            val fragment = ComicsFragment()
            val bundle = Bundle()
            bundle.putInt(HERO_ID, heroId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var heroId: Int? = null
    private lateinit var viewModel: ComicsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.comics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        heroId = arguments?.getInt(HERO_ID) ?: 0
        viewModel = ViewModelProviders.of(this).get(ComicsViewModel::class.java)
    }

}
