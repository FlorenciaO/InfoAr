package com.educacionit.infoar

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.ActivityHomeBinding
import com.educacionit.infoar.fragments.NoticiasFragment
import com.educacionit.infoar.fragments.UsuariosFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private var newsFragment = NoticiasFragment()
    private var usersFragment = UsuariosFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            // Configurar DrawerLayout
/*            val navigationView: NavigationView = navigationView
            val bottomNavigation: BottomNavigationView = bottomNavigation
            val toolbar: MaterialToolbar = toolbar*/

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

            // Cargar fragmento inicial (Home)
            if (savedInstanceState == null) {
                loadFragment(usersFragment)
            }

            // Bottom Navigation Click Listener
            bottomNavigation.setOnItemSelectedListener { bottomItem ->
                when (bottomItem.itemId) {
                    R.id.tab_user -> {
                        loadFragment(usersFragment)
                    }
                    R.id.tab_news -> {
                        loadFragment(newsFragment)
                    }
                }
                true
            }

            // Listener para el Navigation Drawer
            navigationView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_settings -> Snackbar.make(
                        root,
                        "Inicio",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    R.id.nav_home -> Snackbar.make(root, "Inicio", Snackbar.LENGTH_SHORT)
                        .show()

                    R.id.nav_logout -> Snackbar.make(
                        root,
                        "Cerrar Sesión",
                        Snackbar.LENGTH_SHORT
                    ).show()
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
}