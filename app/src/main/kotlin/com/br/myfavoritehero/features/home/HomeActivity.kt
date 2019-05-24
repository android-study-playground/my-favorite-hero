package com.br.myfavoritehero.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.br.myfavoritehero.R
import kotlinx.android.synthetic.main.activity_bottom_navigation.navigation

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val navController: NavController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        navigation.setupWithNavController(navController)
    }
}
