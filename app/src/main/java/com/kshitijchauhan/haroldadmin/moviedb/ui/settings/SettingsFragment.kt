package com.kshitijchauhan.haroldadmin.moviedb.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.kshitijchauhan.haroldadmin.moviedb.R
import com.kshitijchauhan.haroldadmin.moviedb.utils.Constants
import com.kshitijchauhan.haroldadmin.moviedb.utils.SharedPreferencesDelegate
import org.koin.android.ext.android.get
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    private var countryName by SharedPreferencesDelegate(
        get<SharedPreferences>(),
        Constants.KEY_COUNTRY_NAME,
        Locale.getDefault().displayCountry
    )

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_root, rootKey)
        findPreference(getText(R.string.key_country))
            .setOnPreferenceChangeListener { preference, newValue ->
                when (preference.key) {
                    Constants.KEY_COUNTRY_CODE -> {
                        val listPreference = preference as ListPreference
                        val index = listPreference.findIndexOfValue(newValue as String)
                        countryName = listPreference.entries[index].toString()
                    }
                }
                true
            }
    }
}
