package ru.tinkoff.sample_eba

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tinkoff.sample_eba.about.AboutFragment
import ru.tinkoff.sample_eba.base.BaseActivity
import ru.tinkoff.sample_eba.search.SearchFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val fragmentsHolder = mapOf(
        R.id.navigation_search to SearchFragment(),
        R.id.navigation_about to AboutFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(SearchFragment())
        findViewById<BottomNavigationView>(R.id.bottomNav).setOnNavigationItemSelectedListener { menuItem ->
            replaceFragment(fragmentsHolder[menuItem.itemId] ?: error("Unknown menu item."))
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}