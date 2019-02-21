package com.br.myfavoritehero.features.listCharacter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.myfavoritehero.data.interfaces.HeroEventListener
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.features.heroDetails.DetailHeroActivity
import com.br.myfavoritehero.features.listener.EndlessScrollListener
import com.br.myfavoritehero.util.Constants.HERO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_heroes.*
import kotlinx.android.synthetic.main.generic_error_screen.*
import kotlinx.android.synthetic.main.hero_list_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import com.br.myfavoritehero.R

class ListHeroesActivity : AppCompatActivity(), HeroEventListener {

    private lateinit var heroAdapter: HeroAdapter

    val listCharacterViewModel: ListHeroesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_heroes)
        initObservable()
    }

    private fun initObservable(){
        listCharacterViewModel.getHeroes().observe(this, Observer{ stateModel ->

            when(stateModel.status){
                ViewStateModel.Status.ERROR -> {
                    error_screen.visibility = View.VISIBLE
                    listHeroes.visibility = View.GONE
                    shimmer_view_container.visibility = View.GONE
                    shimmer_view_container.stopShimmerAnimation()
                    Timber.d("ERROR: ${stateModel.errors.toString()}")
                }
                ViewStateModel.Status.SUCCESS -> {
                    listHeroes.visibility = View.VISIBLE
                    shimmer_view_container.visibility = View.GONE
                    shimmer_view_container.stopShimmerAnimation()

                    listHeroes.setHasFixedSize(true)
                    val layoutManager = LinearLayoutManager(this)
                    listHeroes.layoutManager = layoutManager
                    heroAdapter = HeroAdapter(stateModel.model!!, this)
                    listHeroes.adapter = heroAdapter
                    listHeroes.addOnScrollListener(object : EndlessScrollListener(layoutManager){
                        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                            listCharacterViewModel.loadMore(totalItemsCount)
                        }
                    })

                }
                ViewStateModel.Status.LOADING -> {
                    shimmer_view_container.visibility = View.VISIBLE
                    shimmer_view_container.startShimmerAnimation()
                    Timber.d("LOADING: ... ")
                }
            }
        })

        listCharacterViewModel.getMore().observe(this, Observer { stateModel ->
            when(stateModel.status){
                ViewStateModel.Status.ERROR -> {
                    Snackbar.make(activity_list_heroes, R.string.error_dialog_title, Snackbar.LENGTH_SHORT).show()
                    Timber.d("ERROR: ${stateModel.errors.toString()}")
                    heroAdapter.stopLoading()
                }
                ViewStateModel.Status.SUCCESS -> {
                    heroAdapter.stopLoading()
                    heroAdapter.updateUI(stateModel.model!!)
                }
                ViewStateModel.Status.LOADING -> {
                    heroAdapter.startLoading()
                    Timber.d("LOADING: ... ")
                }
            }
        })
        listCharacterViewModel.loadHeroes()
    }

    override fun onHeroClicked(hero: Hero) {
        val i = Intent(this, DetailHeroActivity::class.java)
        i.putExtra(HERO, hero)
        startActivity(i)
    }

}