package com.br.myfavoritehero.features.listCharacter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.databinding.ActivityFavoriteListHeroesBinding
import com.br.myfavoritehero.features.heroDetails.DetailHeroActivity
import com.br.myfavoritehero.features.listCharacter.adapter.FavoriteHeroAdapter
import com.br.myfavoritehero.features.listCharacter.viewModel.FavoriteHeroesViewModel
import com.br.myfavoritehero.util.Constants.HERO
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteHeroesFragment : Fragment(), HeroEventListener {

    private lateinit var favoriteHeroAdapter: FavoriteHeroAdapter
    private val favoriteHeroesViewModel: FavoriteHeroesViewModel by viewModel()

    private var _binding: ActivityFavoriteListHeroesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityFavoriteListHeroesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(): Fragment {
            return FavoriteHeroesFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.favoriteHeroes.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(activity,2)
        binding.favoriteHeroes.layoutManager = layoutManager
        favoriteHeroAdapter = FavoriteHeroAdapter(ArrayList(), this)
        binding.favoriteHeroes.adapter = favoriteHeroAdapter

        initObservable()
    }

    private fun initObservable() {

        this.lifecycle.addObserver(favoriteHeroesViewModel)

        favoriteHeroesViewModel.getFavoriteHeroes().observe(viewLifecycleOwner, Observer { heroes ->
            favoriteHeroAdapter.updateUI(heroes)
            if (heroes.isEmpty()) {
                Toast.makeText(activity, "LISTA DE FAVORITOS VAZIA", Toast.LENGTH_LONG).show()
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

    override fun onHeroFavorited(hero: Hero) {
        hero.isFavorite = !hero.isFavorite
        favoriteHeroesViewModel.updateHero(hero)
    }


}