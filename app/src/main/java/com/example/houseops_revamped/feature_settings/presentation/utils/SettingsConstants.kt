package com.example.houseops_revamped.feature_settings.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import com.example.houseops_revamped.feature_settings.presentation.model.SettingsSection
import com.example.houseops_revamped.ui.theme.BlueAccent
import com.example.houseops_revamped.ui.theme.BlueAccentTransparentAlt

object SettingsConstants {

    val settingsSections = listOf(
        SettingsSection("Theme", Icons.Outlined.Collections, BlueAccent, BlueAccentTransparentAlt),
        SettingsSection("Personalization", Icons.Outlined.Settings, BlueAccent, BlueAccentTransparentAlt),
        SettingsSection("About", Icons.Outlined.ContactPage, BlueAccent, BlueAccentTransparentAlt)
    )
}