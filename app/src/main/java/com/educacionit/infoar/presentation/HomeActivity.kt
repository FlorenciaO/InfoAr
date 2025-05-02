package com.educacionit.infoar.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.educacionit.infoar.R
import com.educacionit.infoar.databinding.ActivityHomeBinding
import com.educacionit.infoar.presentation.fragments.NoticiasFragment
import com.educacionit.infoar.presentation.fragments.ServiceFragment
import com.educacionit.infoar.presentation.fragments.SettingsFragment
import com.educacionit.infoar.presentation.fragments.UserMapFragment
import com.educacionit.infoar.presentation.fragments.UserMapFragment.Companion.ID_FRAGMENT
import com.educacionit.infoar.presentation.fragments.UserMapFragment.Companion.PARAM_ADDRESS
import com.educacionit.infoar.presentation.fragments.UserMapFragment.Companion.PARAM_LAT
import com.educacionit.infoar.presentation.fragments.UserMapFragment.Companion.PARAM_LNG
import com.educacionit.infoar.presentation.fragments.UserMapFragment.Companion.PARAM_USERNAME
import com.educacionit.infoar.presentation.fragments.UsuariosFragment
import com.educacionit.infoar.presentation.fragments.communication.UsuariosListener
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity(), UsuariosListener {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding) {
            // Configurar Toolbar con Navigation Drawer
            setSupportActionBar(toolbar)
            val toggle = ActionBarDrawerToggle(
                this@HomeActivity,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            val navHost =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHost.navController

            val topDestinations = setOf(
                R.id.tab_user,
                R.id.tab_news
            )

            createNavGraph(navController, topDestinations)

            val appBarConfiguration = AppBarConfiguration(
                topDestinations,
                drawerLayout
            )

            toolbar.setupWithNavController(navController, appBarConfiguration)

            // Bottom Navigation Click Listener
            bottomNavigation.setOnItemSelectedListener { bottomItem ->
                when (bottomItem.itemId) {
                    R.id.tab_user -> {
                        navController.navigate(R.id.tab_user)
                    }

                    R.id.tab_news -> {
                        navController.navigate(R.id.tab_news)
                    }
                }
                true
            }

            // Listener para el Navigation Drawer
            navigationView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_settings ->
                        navController.navigate(R.id.nav_settings)

                    R.id.nav_home -> Snackbar.make(root, "Inicio", Snackbar.LENGTH_SHORT)
                        .show()

                    R.id.nav_logout -> Snackbar.make(
                        root,
                        "Cerrar Sesión",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    R.id.nav_service -> {
                        navController.navigate(R.id.nav_service)
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    private fun navigateToUserMapFragment(username: String, address: String, lat: Double, lng: Double) {
        val bundle = Bundle().apply {
            putString(PARAM_USERNAME, username)
            putString(PARAM_ADDRESS, address)
            putDouble(PARAM_LAT, lat)
            putDouble(PARAM_LNG, lng)
        }
        navController.navigate(ID_FRAGMENT, bundle)
    }

    private fun createNavGraph(navController: NavController, topDestionations: Set<Int>) {
        val navGraph = navController.createGraph(startDestination = R.id.tab_user) {
            fragment<UsuariosFragment>(R.id.tab_user) {
                label = "Usuarios"
            }
            fragment<NoticiasFragment>(R.id.tab_news) {
                label = "Noticias"
            }
            fragment<ServiceFragment>(R.id.nav_service) {
                label = "Servicio"
            }
            fragment<SettingsFragment>(R.id.nav_settings) {
                label = "Ajustes"
            }
            fragment<UserMapFragment>(ID_FRAGMENT) {
                label = "Dirección de Usuario"
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in topDestionations) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        navController.graph = navGraph
    }

    override fun onGoToMapClicked(username: String, address: String, lat: Double, lng: Double) {
        navigateToUserMapFragment(username, address, lat, lng)
    }
}