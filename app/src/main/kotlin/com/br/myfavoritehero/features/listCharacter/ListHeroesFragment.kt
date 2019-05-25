package com.br.myfavoritehero.features.listCharacter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.features.heroDetails.DetailHeroActivity
import com.br.myfavoritehero.util.Constants.HERO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_heroes.listHeroes
import kotlinx.android.synthetic.main.activity_list_heroes.activity_list_heroes
import kotlinx.android.synthetic.main.generic_error_screen.error_screen
import kotlinx.android.synthetic.main.generic_error_screen.tryAgain
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ListHeroesFragment : Fragment(), HeroEventListener {

    private var heroAdapter: HeroAdapter = HeroAdapter(ArrayList(), this)
    private lateinit var layoutManager: LinearLayoutManager

    val listCharacterViewModel: ListHeroesViewModel by viewModel()

    companion object {
        fun newInstance(): Fragment {
            return ListHeroesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_list_heroes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListHeroes()
        initObservable()
        tryAgain.setOnClickListener {
            listCharacterViewModel.loadHeroes()
        }
    }

    private fun initListHeroes() {
        layoutManager = LinearLayoutManager(activity)
        listHeroes.layoutManager = layoutManager
        listHeroes.adapter = heroAdapter
        listHeroes.setHasFixedSize(true)
    }

    private fun initObservable() {

        this.lifecycle.addObserver(listCharacterViewModel)

        listCharacterViewModel.getHeroes().observe(this, Observer { stateModel ->

            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    error_screen.visibility = View.VISIBLE
                    listHeroes.visibility = View.GONE
                    heroAdapter.stopLoading()
                    Timber.d("ERROR: ${stateModel.errors}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    listHeroes.visibility = View.VISIBLE
                    heroAdapter.stopLoading()
                    stateModel.model?.let {
                        heroAdapter.updateUI(it)
                    }
                    listHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                    error_screen.visibility = View.GONE
                    listHeroes.visibility = View.VISIBLE
                    heroAdapter.startLoading()
                    Timber.d("LOADING: ... ")
                }
            }
        })

        listCharacterViewModel.getMore().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.ERROR -> {
                    Snackbar.make(activity_list_heroes, R.string.error_dialog_title, Snackbar.LENGTH_SHORT).show()
                    Timber.d("ERROR: ${stateModel.errors}")
                    heroAdapter.stopLoading()
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
}