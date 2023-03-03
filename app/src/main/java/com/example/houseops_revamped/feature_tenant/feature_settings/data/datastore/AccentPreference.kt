package com.example.houseops_revamped.feature_tenant.feature_settings.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.houseops_revamped.core.data.datastore.DatastoreConstants
import com.example.houseops_revamped.core.data.datastore.datastore
import com.example.houseops_revamped.core.presentation.model.AccentColor
import com.example.houseops_revamped.core.presentation.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccentPreference(
    private val context: Context
) {

    companion object {
        val PRIMARY_ACCENT_KEY = intPreferencesKey(DatastoreConstants.PRIMARY_ACCENT_KEY)
        val TERTIARY_ACCENT_KEY = intPreferencesKey(DatastoreConstants.TERTIARY_ACCENT_KEY)
    }

    val getPrimaryAccent: Flow<Int?> = context.datastore.data
        .map { preferences ->
            preferences[PRIMARY_ACCENT_KEY] ?: Constants.accentColors[1].darkColor
        }

    val getTertiaryAccent: Flow<Int?> = context.datastore.data
        .map { preferences ->
            preferences[TERTIARY_ACCENT_KEY] ?: Constants.accentColors[1].lightColor
        }

    suspend fun setAccent(accent: AccentColor) {
        context.datastore.edit { preferences ->
            preferences[PRIMARY_ACCENT_KEY] = accent.darkColor
            preferences[TERTIARY_ACCENT_KEY] = accent.lightColor
        }
    }
}















