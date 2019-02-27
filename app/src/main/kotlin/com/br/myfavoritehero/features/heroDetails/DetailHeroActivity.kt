package com.br.myfavoritehero.features.heroDetails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.features.listComics.ComicsFragment
import com.br.myfavoritehero.util.Constants.HERO
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_hero.*
import org.koin.android.ext.android.inject

class DetailHeroActivity : AppCompatActivity() {

    private lateinit var hero : Hero
    private val heroDao : HeroDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)

        setSupportActionBar(toolbar)

        hero = intent.getParcelableExtra(HERO)
        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail()).into(heroImage)
        description.text = hero.description

        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.title = hero.name
        }

        switchVisibility()

        fab_favorite_hero.setOnClickListener{
            if (hero.isFavorite) {
                hero.isFavorite = !hero.isFavorite
                Snackbar.make(scroll_view, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
            }else{
                hero.isFavorite = !hero.isFavorite
                Snackbar.make(scroll_view, R.string.favorited, Snackbar.LENGTH_SHORT).show()
            }
            heroDao.save(hero)
            switchVisibility()
        }

        supportFragmentManager
                .beginTransaction()
                .add(R.id.comicsList,ComicsFragment.newInstance(hero.id))
                .commit()
    }

    private fun switchVisibility(){
        if (hero.isFavorite) {
            fab_favorite_hero.setImageResource(R.drawable.un_favorite)
        }else{
            fab_favorite_hero.setImageResource(R.drawable.favorite)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
