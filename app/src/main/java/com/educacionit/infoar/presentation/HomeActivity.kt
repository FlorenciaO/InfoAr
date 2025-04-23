package com.educacionit.infoar.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.educacionit.infoar.R
import com.educacionit.infoar.databinding.ActivityHomeBinding
import com.educacionit.infoar.presentation.fragments.NoticiasFragment
import com.educacionit.infoar.presentation.fragments.ServiceFragment
import com.educacionit.infoar.presentation.fragments.UsuariosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // Set up toolbar
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

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val topLevelDestinations = setOf(R.id.tab_news, R.id.tab_user)

            createNavGraph(navController, topLevelDestinations, bottomNavigation)

            val appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = topLevelDestinations,
                drawerLayout // ← tell it about the drawer!
            )

            toolbar.setupWithNavController(navController, appBarConfiguration)

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

            navigationView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_settings -> Snackbar.make(root, "Inicio", Snackbar.LENGTH_SHORT).show()
                    R.id.nav_home -> navController.navigate(R.id.tab_user)
                    R.id.nav_logout -> Snackbar.make(root, "Cerrar Sesión", Snackbar.LENGTH_SHORT).show()
                    R.id.nav_service -> navController.navigate(R.id.nav_service)
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    // Método para cambiar fragmentos manualmente
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    private fun createNavGraph(navController: NavController,topLevelDestinations: Set<Int>, bottomNav: BottomNavigationView) {
        val navGraph = navController.createGraph(startDestination = R.id.tab_user) {
            fragment<UsuariosFragment>(R.id.tab_user) {
                label = "Usuarios"
            }
            fragment<NoticiasFragment>(R.id.tab_news) {
                label = "Noticias"
            }
            fragment<ServiceFragment>(R.id.nav_service) {
                label = "Servicio Test"
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in topLevelDestinations) {
                bottomNav.visibility = View.VISIBLE
            } else {
                bottomNav.visibility = View.GONE
            }
        }
        navController.graph = navGraph
    }
}