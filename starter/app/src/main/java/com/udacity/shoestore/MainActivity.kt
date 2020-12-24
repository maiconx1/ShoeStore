package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.login.LoginViewModelFactory
import com.udacity.shoestore.shoelist.ShoeListFragmentDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        viewModelFactory = LoginViewModelFactory()
        Timber.plant(Timber.DebugTree())
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        toolbar.setupWithNavController(navController, appBarConfiguration)

        toolbar.menu.findItem(R.id.logoutMenu).setOnMenuItemClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            navController.navigate(action)
            true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                controller.graph.startDestination, R.id.welcomeFragment, R.id.instructionsFragment -> toolbar.visibility =
                    GONE
                R.id.shoeListFragment -> {
                    toolbar.visibility = VISIBLE
                    toolbar.navigationIcon = null
                    toolbar.menu.findItem(R.id.logoutMenu).isVisible = true
                }
                else -> {
                    toolbar.visibility = VISIBLE
                    toolbar.menu.findItem(R.id.logoutMenu).isVisible = false
                }
            }
        }
    }
}
