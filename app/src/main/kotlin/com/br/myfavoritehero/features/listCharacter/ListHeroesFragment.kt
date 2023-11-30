package com.br.myfavoritehero.features.listCharacter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.databinding.ActivityListHeroesBinding
import com.br.myfavoritehero.features.heroDetails.DetailHeroActivity
import com.br.myfavoritehero.features.listCharacter.adapter.HeroAdapter
import com.br.myfavoritehero.features.listCharacter.viewModel.ListHeroesViewModel
import com.br.myfavoritehero.util.Constants.HERO
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ListHeroesFragment : Fragment(), HeroEventListener {

    private var heroAdapter: HeroAdapter = HeroAdapter(ArrayList(), this)
    private lateinit var layoutManager: GridLayoutManager
    private val listCharacterViewModel: ListHeroesViewModel by viewModel()

    companion object {
        fun newInstance(): Fragment {
            return ListHeroesFragment()
        }
    }

    private var _binding: ActivityListHeroesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityListHeroesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListHeroes()
        initObservable()
        binding.genericError.tryAgain.setOnClickListener {
            listCharacterViewModel.loadHeroes()
        }
    }

    private fun initListHeroes() {
        layoutManager = GridLayoutManager(activity, 2)
        binding.listHeroes.layoutManager = layoutManager
        binding.listHeroes.adapter = heroAdapter
        binding.listHeroes.setHasFixedSize(true)
    }

    private fun initObservable() {

        this.lifecycle.addObserver(listCharacterViewModel)

        listCharacterViewModel.getHeroes().observe(viewLifecycleOwner, Observer { stateModel ->

            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    heroAdapter.stopLoading()
                    binding.genericError.errorScreen.visibility = View.VISIBLE
                    binding.listHeroes.visibility = View.GONE
                    Timber.d("ERROR: ${stateModel.errors}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    heroAdapter.stopLoading()
                    binding.listHeroes.visibility = View.VISIBLE
                    binding.genericError.errorScreen.visibility = View.GONE
                    stateModel.model?.let {
                        heroAdapter.updateUI(it)
                    }
                    binding.listHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            if (dy > 0) {
                                val visibleItemCount = layoutManager.childCount
                                val totalItemCount = layoutManager.itemCount
                                val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && !heroAdapter.isLoading()) {
                                    listCharacterViewModel.loadMore(heroAdapter.itemCount)
                                }
                            }
                        }
                    })
                }
                ViewStateModel.Status.LOADING -> {
                    heroAdapter.startLoading()
                    binding.genericError.errorScreen.visibility = View.GONE
                    binding.listHeroes.visibility = View.VISIBLE
                    Timber.d("LOADING: ... ")
                }
            }
        })

        listCharacterViewModel.getMore().observe(viewLifecycleOwner, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    heroAdapter.stopLoading()
                    Snackbar.make(
                        binding.root,
                        R.string.error_dialog_title,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Timber.d("ERROR: ${stateModel.errors}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    heroAdapter.stopLoading()
                    stateModel.model?.let {
                        heroAdapter.updateUI(it)
                    }
                }
                ViewStateModel.Status.LOADING -> {
                    heroAdapter.startLoading()
                    Timber.d("LOADING: ... ")
                }
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
        if (hero.isFavorite) {
            Snackbar.make(binding.root, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, R.string.favorited, Snackbar.LENGTH_SHORT).show()
        }
        hero.isFavorite = !hero.isFavorite
        heroAdapter.updateHero(hero)
        listCharacterViewModel.updateHero(hero)
    }
}