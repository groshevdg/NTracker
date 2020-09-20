package ru.groshevdg.utils

import android.content.Context
import androidx.preference.PreferenceManager
import javax.inject.Inject

class SharedPrefsUtils @Inject constructor(private var context: Context) {

    fun getSourcePrefByKey(key: String) : Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, true)
    }
}