package com.br.myfavoritehero.features.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.br.myfavoritehero.R
import com.br.myfavoritehero.databinding.ActivityBottomNavigationBinding
import com.br.myfavoritehero.features.listCharacter.FavoriteHeroesFragment
import com.br.myfavoritehero.features.listCharacter.ListHeroesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var listHeroesFragment: Fragment
    private lateinit var favoriteListHeroesFragment: Fragment
    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listHeroesFragment = ListHeroesFragment.newInstance()
        favoriteListHeroesFragment = FavoriteHeroesFragment.newInstance()

        switchFragment(listHeroesFragment)
        binding.navigation.setOnNavigationItemSelectedListener(this)
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
