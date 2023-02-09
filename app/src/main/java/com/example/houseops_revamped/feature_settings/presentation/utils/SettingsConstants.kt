package com.example.houseops_revamped.feature_settings.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.houseops_revamped.feature_settings.presentation.model.SettingsSectionModel
import com.example.houseops_revamped.ui.theme.*

object SettingsConstants {

    val settingsSections = listOf(
        SettingsSectionModel("Theme", Icons.Outlined.DarkMode, BlueAccent, BlueAccentTransparentAlt),
        SettingsSectionModel("Personalization", Icons.Outlined.Colorize, LightYellow, LightYellowDull),
        SettingsSectionModel("About", Icons.Outlined.ContactPage, Violet, VioletDull),
        SettingsSectionModel("Danger Zone", Icons.Outlined.Warning, RedOrange, RedOrangeDull)
    )

    val themeOptions = listOf(
        "Dark Theme",
        "Light Theme",
        "Follow System"
    )
}