package com.br.myfavoritehero.features.listCharacter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.ViewStateModel
import kotlinx.android.synthetic.main.activity_list_heroes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListHeroesActivity : AppCompatActivity() {

    val listCharacterViewModel: ListHeroesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_heroes)

        lifecycle.addObserver(listCharacterViewModel)
        initObserver()

    }

    fun initObserver(){
        listCharacterViewModel.getHeroes().observe(this, Observer{ stateModel ->

            when(stateModel.status){
                ViewStateModel.Status.ERROR -> {
                    Log.d("ERROR:", stateModel.errors.toString())
                }
                ViewStateModel.Status.SUCCESS -> {
                    progressBarList.visibility = View.GONE

                    listHeroes.setHasFixedSize(true)
                    val layoutManager = LinearLayoutManager(this)
                    listHeroes.layoutManager = layoutManager
                    val heroAdapter = HeroAdapter(stateModel.model!!, R.layout.hero_item, {})
                    listHeroes.adapter = heroAdapter
                }
                ViewStateModel.Status.LOADING -> {
                    progressBarList.visibility = View.VISIBLE
                    Log.d("LOADING:", " ... ")
                }
            }
        })
    }
}
