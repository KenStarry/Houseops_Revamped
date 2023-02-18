package com.example.houseops_revamped.feature_settings.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.houseops_revamped.feature_settings.presentation.model.SettingsSectionItem
import com.example.houseops_revamped.feature_settings.presentation.model.SettingsSectionModel
import com.example.houseops_revamped.ui.theme.*

object SettingsConstants {

    val settingsSections = listOf(
        SettingsSectionModel(
            "Theme",
            Icons.Outlined.DarkMode,
            BlueAccent,
            BlueAccentTransparentAlt
        ),
        SettingsSectionModel(
            "Personalization",
            Icons.Outlined.Colorize,
            LightYellow,
            LightYellowDull
        ),
        SettingsSectionModel("About", Icons.Outlined.ContactPage, Violet, VioletDull),
        SettingsSectionModel("Danger Zone", Icons.Outlined.Warning, RedOrange, RedOrangeDull)
    )

    //  theme section
    val themeOptions = listOf(
        "Dark Theme",
        "Light Theme",
        "Follow System"
    )

    //  danger section
    val dangerOptions = listOf(
        SettingsSectionItem(title = "Logout", icon = Icons.Outlined.Logout),
        SettingsSectionItem(title = "Delete Account", icon = Icons.Outlined.DeleteForever)
    )


}