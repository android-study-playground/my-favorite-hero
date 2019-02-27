package com.br.myfavoritehero.features.listCharacter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.features.heroDetails.DetailHeroActivity
import com.br.myfavoritehero.features.listCharacter.adapter.FavoriteHeroAdapter
import com.br.myfavoritehero.features.listCharacter.viewModel.FavoriteHeroesViewModel
import com.br.myfavoritehero.util.Constants.HERO
import kotlinx.android.synthetic.main.activity_favorite_list_heroes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteHeroesFragment : Fragment(), HeroEventListener {

    private lateinit var favoriteHeroAdapter: FavoriteHeroAdapter
    private val favoriteHeroesViewModel: FavoriteHeroesViewModel by viewModel()

    companion object {
        fun newInstance(): Fragment{
            return FavoriteHeroesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.activity_favorite_list_heroes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteHeroes.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        favoriteHeroes.layoutManager = layoutManager
        favoriteHeroAdapter = FavoriteHeroAdapter(ArrayList(), this)
        favoriteHeroes.adapter = favoriteHeroAdapter

        initObservable()
    }

    private fun initObservable(){

        this.lifecycle.addObserver(favoriteHeroesViewModel)

        favoriteHeroesViewModel.getFavoriteHeroes().observe(this, Observer{ heroes ->
            if (heroes.isEmpty()){
                Toast.makeText(activity, "LISTA DE FAVORITOS VAZIA", Toast.LENGTH_LONG).show()
            }else{
                favoriteHeroAdapter.updateUI(heroes)
            }
        })

    }

    override fun onHeroClicked(hero: Hero) {
        activity.let {
            val i = Intent(activity, DetailHeroActivity::class.java)
            i.putExtra(HERO, hero)
            startActivity(i)
        }
    }

}