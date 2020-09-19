package ru.groshevdg.navigation

import android.content.Context
import kotlinx.android.synthetic.main.activity_main.*
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.ui.news.NewsListFragment
import ru.groshevdg.ui.WeatherFragment
import javax.inject.Inject

class Navigator @Inject constructor(private val context: Context) {
    private val newsFragment = NewsListFragment()
    private val weatherFragment = WeatherFragment()
    var currentFragment = 0

    companion object {
        const val NEWS_FRAGMENT = 0
        const val WEATHER_FRAGMENT = 1
    }

    fun navigateTo(_fragment: Int) {
        val containerId = (context as ApplicationActivity).amFragmentContainer
        val fragment = if (_fragment == NEWS_FRAGMENT) newsFragment else weatherFragment
        currentFragment = _fragment

        context.supportFragmentManager.beginTransaction()
            .replace(containerId.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}