package ru.groshevdg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.groshevdg.App
import ru.groshevdg.R
import ru.groshevdg.di.components.DaggerActivityComponent
import ru.groshevdg.di.modules.ActivityModule
import ru.groshevdg.di.modules.NavModule
import ru.groshevdg.navigation.Navigator
import javax.inject.Inject

class ApplicationActivity @Inject constructor() : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDagger()
        amBottomNavView.setOnNavigationItemSelectedListener(this)
        navigator.navigateTo(Navigator.NEWS_FRAGMENT)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.news_item -> {
                navigator.navigateTo(Navigator.NEWS_FRAGMENT)
                true
            }
            R.id.weather_item -> {
                navigator.navigateTo(Navigator.WEATHER_FRAGMENT)
                true
            }
            else -> false
        }
    }

    private fun setupDagger() {
        val activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(App.instance.appComponent)
            .activityModule(ActivityModule(this))
            .navModule(NavModule())
            .build()

        activityComponent.inject(this)
    }

    override fun onBackPressed() {
        if (navigator.currentFragment == Navigator.NEWS_FRAGMENT) {
            finish()
        }
        else {
            navigator.currentFragment = Navigator.NEWS_FRAGMENT
            super.onBackPressed()
        }
    }
}