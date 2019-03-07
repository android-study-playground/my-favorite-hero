package com.br.myfavoritehero.features.heroDetails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.features.listComics.ComicsFragment
import com.br.myfavoritehero.util.Constants.HERO
import com.br.myfavoritehero.util.SharedPreferencesHelper
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_hero.fab_favorite_hero
import kotlinx.android.synthetic.main.activity_detail_hero.toolbar
import kotlinx.android.synthetic.main.activity_detail_hero.heroImage
import kotlinx.android.synthetic.main.activity_detail_hero.description
import kotlinx.android.synthetic.main.activity_detail_hero.scroll_view

class DetailHeroActivity : AppCompatActivity() {

    private lateinit var hero : Hero
    private var favorited : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)

        setSupportActionBar(toolbar)

        hero = intent.getParcelableExtra(HERO)
        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail()).into(heroImage)
        description.text = hero.description

        favorited = SharedPreferencesHelper.isFavorited(this,hero.id)

        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.title = hero.name
        }

        switchVisibility()

        fab_favorite_hero.setOnClickListener{
            if (favorited) {
                SharedPreferencesHelper.setFavorite(this, hero.id, false)
                Snackbar.make(scroll_view, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
                favorited = !favorited
            }else{
                fab_favorite_hero.setImageResource(R.drawable.un_favorite)
                SharedPreferencesHelper.setFavorite(this,hero.id)
                Snackbar.make(scroll_view, R.string.favorited, Snackbar.LENGTH_SHORT).show()
                favorited = !favorited
            }
            switchVisibility()
        }

        supportFragmentManager
                .beginTransaction()
                .add(R.id.comicsList,ComicsFragment.newInstance(hero.id))
                .commit()
    }

    private fun switchVisibility(){
        if (favorited) {
            fab_favorite_hero.setImageResource(R.drawable.un_favorite)
        }else{
            fab_favorite_hero.setImageResource(R.drawable.un_favorite)
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
