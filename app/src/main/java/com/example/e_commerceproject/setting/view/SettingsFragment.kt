package com.example.e_commerceproject.setting.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.e_commerceproject.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}