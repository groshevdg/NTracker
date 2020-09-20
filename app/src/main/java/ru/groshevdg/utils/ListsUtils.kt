package ru.groshevdg.utils

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
}