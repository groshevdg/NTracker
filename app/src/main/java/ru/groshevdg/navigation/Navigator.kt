package ru.groshevdg.navigation

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.ui.news.NewsListFragment
import ru.groshevdg.ui.WeatherFragment
import ru.groshevdg.ui.settings.SettingsFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val context: Context) {
    private val newsFragment = NewsListFragment()
    private val weatherFragment = WeatherFragment()
    private val settingsFragment = SettingsFragment()
    var currentFragment = 0

    companion object {
        const val NEWS_FRAGMENT = 0
        const val WEATHER_FRAGMENT = 1
        const val SETTINGS_FRAGMENT = 2
    }

    fun navigateTo(_fragment: Int) {
        val containerId = (context as ApplicationActivity).amFragmentContainer.id
        val fragment = if (_fragment == NEWS_FRAGMENT) newsFragment else weatherFragment
        currentFragment = _fragment

        context.supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()

        if (context.amBottomNavView.visibility == View.GONE) {
            context.amBottomNavView.visibility = View.VISIBLE
        }
    }

    fun openSettingsFragment() {
        val containerId = (context as ApplicationActivity).amFragmentContainer.id
        currentFragment = SETTINGS_FRAGMENT

        context.supportFragmentManager.beginTransaction()
            .replace(containerId, settingsFragment)
            .commit()

        context.amBottomNavView.visibility = View.GONE
    }
}