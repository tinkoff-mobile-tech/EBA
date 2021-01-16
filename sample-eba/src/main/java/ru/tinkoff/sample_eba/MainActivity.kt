package ru.tinkoff.sample_eba

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tinkoff.eba.R
import ru.tinkoff.sample_eba.about.AboutFragment
import ru.tinkoff.sample_eba.base.BaseActivity
import ru.tinkoff.sample_eba.search.SearchFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(SearchFragment())
        findViewById<BottomNavigationView>(R.id.bottomNav).setOnNavigationItemSelectedListener { menuItem ->
            val fragment = when(menuItem.itemId) {
                R.id.navigation_search -> SearchFragment()
                R.id.navigation_about -> AboutFragment()
                else -> error("Unknown menu item.")
            }
            replaceFragment(fragment)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}