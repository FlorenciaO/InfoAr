package com.educacionit.infoar

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.ActivityHomeBinding
import com.educacionit.infoar.fragments.NoticiasFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Configurar DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)

        // Configurar Toolbar con Navigation Drawer
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Cargar fragmento inicial (Home)
        if (savedInstanceState == null) {
            loadFragment(NoticiasFragment())
        }

        // Bottom Navigation Click Listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            TODO("Una vez creado el bottom_nav_menu.xml, setear fragments")
            when (item.itemId) {

            }
            true
        }

        // Listener para el Navigation Drawer
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_settings -> Snackbar.make(binding.root, "Inicio", Snackbar.LENGTH_SHORT).show()
                R.id.nav_home -> Snackbar.make(binding.root, "Inicio", Snackbar.LENGTH_SHORT).show()
                R.id.nav_logout -> Snackbar.make(binding.root, "Cerrar Sesión", Snackbar.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    // Método para cambiar fragmentos manualmente
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}