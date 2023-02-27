package com.example.bludappproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog

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
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()

                val gsc = GoogleSignIn.getClient(this,gso)

                gsc.signOut().addOnSuccessListener {
                    Toast.makeText(this, getString(R.string.logout_success), Toast.LENGTH_LONG).show()
                    SharedPrefsWrapper.isLoggedIn = false
                    Navigation.findNavController(
                        this@MainActivity,
                        R.id.nav_host_fragment_content_main
                    ).navigate(R.id.getStartedFragment)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.currentDestination
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.forEach { fragment ->
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }





}