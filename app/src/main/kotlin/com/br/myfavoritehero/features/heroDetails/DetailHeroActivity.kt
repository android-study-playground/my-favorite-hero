package com.br.myfavoritehero.features.heroDetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.features.heroDetails.viewModel.HeroDetailViewModel
import com.br.myfavoritehero.features.listComics.ComicsFragment
import com.br.myfavoritehero.util.Constants.HERO
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.br.myfavoritehero.util.getLargePortraitThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_hero.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHeroActivity : AppCompatActivity() {

    private lateinit var hero: Hero
    private val heroDetailViewModel: HeroDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)

        setSupportActionBar(toolbar)

        hero = intent.getParcelableExtra(HERO)
        val heroFromDatabse = heroDetailViewModel.getHero(hero.id)

        heroFromDatabse.let { hero = heroFromDatabse }

        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail())
            .transform(BlurTransformation(this)).into(heroImageBackground)
        Picasso.get().load(hero.thumbnail.path.getLargePortraitThumbnail()).into(heroImage)
        name.text = hero.name

        if (hero.description.isEmpty()) {
            history.visibility = View.GONE
            description.visibility = View.GONE
        } else {
            description.text = hero.description
        }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = hero.name
        }

        switchVisibility()

        fab_favorite_hero.setOnClickListener {
            if (hero.isFavorite) {
                hero.isFavorite = !hero.isFavorite
                Snackbar.make(scroll_view, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
            } else {
                hero.isFavorite = !hero.isFavorite
                Snackbar.make(scroll_view, R.string.favorited, Snackbar.LENGTH_SHORT).show()
            }
            switchVisibility()
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.comicsList, ComicsFragment.newInstance(hero.id))
            .commit()
    }

    override fun onStop() {
        super.onStop()
        heroDetailViewModel.updateHero(hero)
    }

    private fun switchVisibility() {
        if (hero.isFavorite) {
            fab_favorite_hero.setImageResource(R.drawable.un_favorite)
        } else {
            fab_favorite_hero.setImageResource(R.drawable.favorite)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
