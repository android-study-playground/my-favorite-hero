package com.br.myfavoritehero.features.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.br.myfavoritehero.R
import com.br.myfavoritehero.features.listCharacter.FavoriteHeroesFragment
import com.br.myfavoritehero.features.listCharacter.ListHeroesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var listHeroesFragment: Fragment
    private lateinit var favoriteListHeroesFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        listHeroesFragment = ListHeroesFragment.newInstance()
        favoriteListHeroesFragment = FavoriteHeroesFragment.newInstance()

        switchFragment(listHeroesFragment)
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.heroes -> {
                switchFragment(listHeroesFragment)
            }
            R.id.favorite -> {
                switchFragment(favoriteListHeroesFragment)
            }
        }
        return true
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
