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
        SettingsSectionModel(
            "Miscellaneous",
            Icons.Outlined.MiscellaneousServices,
            Violet,
            VioletDull
        ),
        SettingsSectionModel("Danger Zone",
            Icons.Outlined.Warning,
            RedOrange,
            RedOrangeDull)
    )

    //  theme section
    val themeOptions = listOf(
        SettingsSectionItem("Dark Theme", Icons.Outlined.Nightlight),
        SettingsSectionItem("Light Theme", Icons.Outlined.LightMode),
        SettingsSectionItem("Follow System", Icons.Outlined.Light)
    )

    //  danger section
    val dangerOptions = listOf(
        SettingsSectionItem(title = "Logout", icon = Icons.Outlined.Logout),
        SettingsSectionItem(title = "Delete Account", icon = Icons.Outlined.DeleteForever)
    )


}