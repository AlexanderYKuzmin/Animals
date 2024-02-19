package com.kuzmin.animals.ui

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kuzmin.animals.R
import com.kuzmin.animals.common.R.*
import com.kuzmin.animals.common.R.id.favorite_nav_graph
import com.kuzmin.animals.common.R.id.home_nav_graph
import com.kuzmin.animals.common.R.id.settings_nav_graph
import com.kuzmin.animals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //println("MainActivity ${Firebase.database.toString()}")

        window.statusBarColor = ContextCompat.getColor(this, color.color_primary_dark)

        setupToolbar()

        val navView = binding.navView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                home_nav_graph,
                favorite_nav_graph,
                settings_nav_graph
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val startFragmentBundle = bundleOf()
        navController.setGraph(navController.graph, startFragmentBundle)

    }


    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        val title = binding.toolbar.getChildAt(0) as TextView
        val titleTypeFace: Typeface = Typeface.createFromAsset(assets, "fonts/kittens.otf")

        with(title) {
            typeface = titleTypeFace
            textSize = 24f
            setTextColor(ContextCompat.getColor(this@MainActivity, color.color_on_primary))
            setPadding(80, 0, 0, 0)
        }
    }

    fun renderUi(appState: AppState) {

    }
}