package com.br.myfavoritehero.features.heroDetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.Constants.HERO
import com.br.myfavoritehero.util.SharedPreferencesHelper
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_hero.*
import timber.log.Timber

class DetailHeroActivity : AppCompatActivity() {

    private lateinit var hero : Hero
    private var mOptionsMenu: Menu? = null
    private var favorited : Boolean = false
    private var hated : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)
        setSupportActionBar(toolbar)

        hero = intent.getParcelableExtra(HERO)
        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail()).into(heroImage)
        description.text = hero.description

        favorited = SharedPreferencesHelper.isFavorited(this,hero.id)
        hated = SharedPreferencesHelper.isHated(this,hero.id)

        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.title = hero.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mOptionsMenu = menu
        this.menuInflater.inflate(R.menu.menu, mOptionsMenu)
        switchVisibility()
        return super.onCreateOptionsMenu(mOptionsMenu)
    }

    private fun switchVisibility(){
        if ( favorited ) {
            mOptionsMenu?.findItem(R.id.unFavorite)?.isVisible = true
            mOptionsMenu?.findItem(R.id.favorite)?.isVisible = false
        }else{
            mOptionsMenu?.findItem(R.id.favorite)?.isVisible = true
            mOptionsMenu?.findItem(R.id.unFavorite)?.isVisible = false
        }

        if ( hated ){
            mOptionsMenu?.findItem(R.id.unHate)?.isVisible = true
            mOptionsMenu?.findItem(R.id.hate)?.isVisible = false
        }else{
            mOptionsMenu?.findItem(R.id.hate)?.isVisible = true
            mOptionsMenu?.findItem(R.id.unHate)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.favorite -> {
                SharedPreferencesHelper.setFavorite(this,hero.id)
                Snackbar.make(scroll_view, R.string.favorited, Snackbar.LENGTH_SHORT).show()
                favorited = !favorited
            }
            R.id.unFavorite -> {
                SharedPreferencesHelper.setFavorite(this,hero.id, false)
                Snackbar.make(scroll_view, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
                favorited = !favorited
            }
            R.id.hate-> {
                SharedPreferencesHelper.setHate(this,hero.id)
                Snackbar.make(scroll_view, R.string.hated, Snackbar.LENGTH_SHORT).show()
                hated = !hated
            }
            R.id.unHate -> {
                SharedPreferencesHelper.setHate(this,hero.id,false)
                Snackbar.make(scroll_view, R.string.unHated, Snackbar.LENGTH_SHORT).show()
                hated = !hated
            }
            else -> {
                Timber.d("I DON'T KNOW WHAT HAPPENS")
            }
        }
        switchVisibility()
        return super.onOptionsItemSelected(item)
    }
}
