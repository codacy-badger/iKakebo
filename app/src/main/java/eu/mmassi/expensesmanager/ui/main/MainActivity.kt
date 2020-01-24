package eu.mmassi.expensesmanager.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import eu.mmassi.expensesmanager.BaseActivity
import eu.mmassi.expensesmanager.R
import eu.mmassi.expensesmanager.appComponent
import eu.mmassi.expensesmanager.di.app.ViewModelProviderFactory
import eu.mmassi.expensesmanager.di.main.MainComponent
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_view
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    val mainComponent: MainComponent by lazy {
        appComponent.mainComponent().create()
    }

    private val navController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(navController, drawer_layout)
        setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.main_navigation, true).build()
                    navController.navigate(R.id.profileFragment, null, navOptions)
                }
                R.id.nav_posts -> {
                    if (navController.currentDestination?.id != R.id.postsFragment) {
                        navController.navigate(R.id.postsFragment)
                    }
                }
            }

            menuItem.isChecked = true
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                true
            }
            android.R.id.home -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, drawer_layout)
    }
}
