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

    //  personalization section
    val personalizationOptions = listOf(
        SettingsSectionItem("Accent Color", Icons.Outlined.FormatPaint)
    )

    //  miscellaneous section
    val miscOptions = listOf(
        SettingsSectionItem("About", Icons.Outlined.AccountBox),
        SettingsSectionItem("Leave us a feedback", Icons.Outlined.Feedback),
        SettingsSectionItem("Share", Icons.Outlined.Share),
        SettingsSectionItem("Rate us on Google Play", Icons.Outlined.StarRate),
        SettingsSectionItem("Version", Icons.Outlined.History),
    )

    //  danger section
    val dangerOptions = listOf(
        SettingsSectionItem(title = "Logout", icon = Icons.Outlined.Logout),
        SettingsSectionItem(title = "Delete Account", icon = Icons.Outlined.DeleteForever)
    )


}