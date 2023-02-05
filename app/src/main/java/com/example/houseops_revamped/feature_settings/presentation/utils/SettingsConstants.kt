package com.example.houseops_revamped.feature_settings.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.Settings
import com.example.houseops_revamped.feature_settings.presentation.model.SettingsSectionModel
import com.example.houseops_revamped.ui.theme.BlueAccent
import com.example.houseops_revamped.ui.theme.BlueAccentTransparentAlt

object SettingsConstants {

    val settingsSections = listOf(
        SettingsSectionModel("Theme", Icons.Outlined.Collections, BlueAccent, BlueAccentTransparentAlt),
        SettingsSectionModel("Personalization", Icons.Outlined.Settings, BlueAccent, BlueAccentTransparentAlt),
        SettingsSectionModel("About", Icons.Outlined.ContactPage, BlueAccent, BlueAccentTransparentAlt)
    )

    val themeOptions = listOf(
        "Dark Theme",
        "Light Theme",
        "Follow System"
    )
}