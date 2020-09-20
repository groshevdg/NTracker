package ru.groshevdg.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ru.groshevdg.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey)
    }
}