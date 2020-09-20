package ru.groshevdg.utils

import ru.groshevdg.R
import ru.groshevdg.config.Config
import javax.inject.Inject

class ListsUtils @Inject constructor(private val prefsUtils: SharedPrefsUtils) {

    fun getSourceMap() : Map<String, Boolean> {
        return mapOf(
            Config.auto to prefsUtils.getSourcePrefByKey(Config.auto),
            Config.auto_racing to prefsUtils.getSourcePrefByKey(Config.auto_racing),
            Config.army to prefsUtils.getSourcePrefByKey(Config.army),
            Config.world to prefsUtils.getSourcePrefByKey(Config.world),
            Config.gadgets to prefsUtils.getSourcePrefByKey(Config.gadgets),
            Config.communal to prefsUtils.getSourcePrefByKey(Config.communal),
            Config.health to prefsUtils.getSourcePrefByKey(Config.health),
            Config.games to prefsUtils.getSourcePrefByKey(Config.games),
            Config.internet to prefsUtils.getSourcePrefByKey(Config.internet),
            Config.cyber_sport to prefsUtils.getSourcePrefByKey(Config.cyber_sport),
            Config.movies to prefsUtils.getSourcePrefByKey(Config.movies),
            Config.koronavirus to prefsUtils.getSourcePrefByKey(Config.koronavirus),
            Config.cosmos to prefsUtils.getSourcePrefByKey(Config.cosmos),
            Config.music to prefsUtils.getSourcePrefByKey(Config.music),
            Config.science to prefsUtils.getSourcePrefByKey(Config.science),
            Config.realty to prefsUtils.getSourcePrefByKey(Config.realty),
            Config.society to prefsUtils.getSourcePrefByKey(Config.society),
            Config.politics to prefsUtils.getSourcePrefByKey(Config.politics),
            Config.incident to prefsUtils.getSourcePrefByKey(Config.incident),
            Config.travels to prefsUtils.getSourcePrefByKey(Config.travels),
            Config.religion to prefsUtils.getSourcePrefByKey(Config.religion),
            Config.sport to prefsUtils.getSourcePrefByKey(Config.sport),
            Config.theaters to prefsUtils.getSourcePrefByKey(Config.theaters),
            Config.computers to prefsUtils.getSourcePrefByKey(Config.computers),
            Config.vehicle to prefsUtils.getSourcePrefByKey(Config.vehicle),
            Config.finances to prefsUtils.getSourcePrefByKey(Config.finances),
            Config.showbusiness to prefsUtils.getSourcePrefByKey(Config.showbusiness),
            Config.ecology to prefsUtils.getSourcePrefByKey(Config.ecology),
            Config.business to prefsUtils.getSourcePrefByKey(Config.business),
            Config.energy to prefsUtils.getSourcePrefByKey(Config.energy)
        )
    }

    companion object {
        val sourceNames = mutableMapOf(
            Config.auto to R.string.auto_title,
            Config.auto_racing to R.string.auto_racing_title,
            Config.army to R.string.army_title,
            Config.world to R.string.world_title,
            Config.gadgets to R.string.gadgets_title,
            Config.communal to R.string.communal_title,
            Config.health to R.string.health_title,
            Config.games to R.string.games_title,
            Config.internet to R.string.internet_title,
            Config.cyber_sport to R.string.cyber_sport_title,
            Config.movies to R.string.movies_title,
            Config.koronavirus to R.string.koronavirus_title,
            Config.cosmos to R.string.cosmos_title,
            Config.music to R.string.music_title,
            Config.science to R.string.science_title,
            Config.realty to R.string.realty_title,
            Config.society to R.string.society_title,
            Config.politics to R.string.politics_title,
            Config.incident to R.string.incident_title,
            Config.travels to R.string.travels_title,
            Config.religion to R.string.religion_title,
            Config.sport to R.string.sport_title,
            Config.theaters to R.string.theaters_title,
            Config.computers to R.string.computers_title,
            Config.vehicle to R.string.vehicle_title,
            Config.finances to R.string.finances_title,
            Config.showbusiness to R.string.showbusiness_title,
            Config.ecology to R.string.ecology_title,
            Config.business to R.string.business_title,
            Config.energy to R.string.energy_title
        )
    }
}