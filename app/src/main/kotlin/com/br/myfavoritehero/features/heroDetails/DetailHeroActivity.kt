package com.br.myfavoritehero.features.heroDetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.databinding.ActivityDetailHeroBinding
import com.br.myfavoritehero.features.heroDetails.viewModel.HeroDetailViewModel
import com.br.myfavoritehero.features.listComics.ComicsFragment
import com.br.myfavoritehero.util.Constants.HERO
import com.br.myfavoritehero.util.getLargeLandscapeThumbnail
import com.br.myfavoritehero.util.getLargePortraitThumbnail
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHeroActivity : AppCompatActivity() {

    private var hero: Hero = Hero()
    private val heroDetailViewModel: HeroDetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        intent.getParcelableExtra<Hero>(HERO) ?.let{
            hero = it
        }
        val heroFromDatabse = heroDetailViewModel.getHero(hero.id)

        heroFromDatabse.let { hero = heroFromDatabse }

        Picasso.get().load(hero.thumbnail.path.getLargeLandscapeThumbnail())
            .transform(BlurTransformation(this)).into(binding.heroImageBackground)
        Picasso.get().load(hero.thumbnail.path.getLargePortraitThumbnail()).into(binding.heroImage)
        binding.name.text = hero.name

        if (hero.description.isEmpty()) {
            binding.history.visibility = View.GONE
            binding.description.visibility = View.GONE
        } else {
            binding.description.text = hero.description
        }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = hero?.name
        }

        switchVisibility()

        binding.fabFavoriteHero.setOnClickListener {
            if (hero?.isFavorite == true) {
                hero?.isFavorite = !hero!!.isFavorite
                Snackbar.make(binding.scrollView, R.string.unFavorited, Snackbar.LENGTH_SHORT).show()
            } else {
                hero?.isFavorite = !hero!!.isFavorite
                Snackbar.make(binding.scrollView, R.string.favorited, Snackbar.LENGTH_SHORT).show()
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
            binding.fabFavoriteHero.setImageResource(R.drawable.un_favorite)
        } else {
            binding.fabFavoriteHero.setImageResource(R.drawable.favorite)
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
