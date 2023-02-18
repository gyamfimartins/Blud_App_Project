package com.example.bludappproject

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.bludappproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setNav()
    }

    private fun setNav() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(binding) {
                when (destination.id) {
                    R.id.getStartedFragment -> {
                        binding.toolbar.visibility = View.GONE
                        binding.mainview.icPeople.visibility = View.GONE
                        binding.mainview.textViewTitle.visibility = View.GONE
                    }
                    R.id.loginFragment -> {
                        binding.toolbar.visibility = View.GONE
                        binding.mainview.textViewTitle.setText(getString(R.string.login))
                        binding.mainview.icPeople.visibility = View.VISIBLE
                        binding.mainview.textViewTitle.visibility = View.VISIBLE
                    }
                    R.id.settingsFragment -> {
                        binding.toolbar.visibility = View.GONE
                        binding.mainview.textViewTitle.setText(getString(R.string.action_settings))
                        binding.mainview.icPeople.visibility = View.VISIBLE
                        binding.mainview.textViewTitle.visibility = View.VISIBLE
                    }
                    R.id.dashboardFragment -> {
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        binding.toolbar.visibility = View.VISIBLE
                        binding.mainview.icPeople.visibility = View.GONE
                        binding.mainview.textViewTitle.visibility = View.GONE
                    }
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Navigation.findNavController(
                    this@MainActivity,
                    R.id.nav_host_fragment_content_main
                ).navigate(R.id.settingsFragment)
                return true
            }
            R.id.action_more -> {

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}