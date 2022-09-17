package com.guilhermemarx14.mymovieapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.guilhermemarx14.mymovieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar
    lateinit var drawer: DrawerLayout
    lateinit var navDrawer: NavigationView
    lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbar()
        setupNavigationComponents()
        applyTheme()

    }

    private fun applyTheme(){
        //navHost.context?.theme?.applyStyle(R.style.Theme_MyMovieApp_Fragment,true)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupNavigationComponents() {
        navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        navDrawer.setupWithNavController(navController)
        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.movieListFragment ->{
                    Toast.makeText(this, getString(R.string.list_title), Toast.LENGTH_SHORT).show()
                    drawer.close()
                }

                else -> Toast.makeText(this, getString(R.string.list_title), Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    private fun setupBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        drawer = binding.root as DrawerLayout
        navDrawer = binding.navigationView
        toolbar = binding.appBar
        titleTextView = binding.myToolbarTitle

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.search -> {
                Toast.makeText(this, getString(R.string.search), Toast.LENGTH_SHORT).show()
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }

}