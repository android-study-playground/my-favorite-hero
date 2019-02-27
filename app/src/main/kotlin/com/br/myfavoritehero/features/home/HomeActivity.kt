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

    private var fragment: Fragment =  ListHeroesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        navigationView.setOnNavigationItemSelectedListener(this)
        switchFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.heroes -> {
                fragment = ListHeroesFragment.newInstance()
            }
            R.id.favorite -> {
                fragment = FavoriteHeroesFragment.newInstance()
            }
        }
        switchFragment()
        return true
    }

    private fun switchFragment(){
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations( android.R.anim.fade_in, android.R.anim.fade_out )
                .replace(R.id.container, fragment)
                .commit()
    }

}
