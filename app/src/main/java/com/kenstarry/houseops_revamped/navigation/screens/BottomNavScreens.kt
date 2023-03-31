package com.kenstarry.houseops_revamped.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Bookmarks
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.sharp.Timelapse
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNavScreens(
        route = "home_screen",
        title = "Home",
        icon = Icons.Sharp.Home
    )

    object Booked : BottomNavScreens(
        route = "booked_screen",
        title = "Booked",
        icon = Icons.Sharp.Timelapse
    )

    object Bookmarks : BottomNavScreens(
        route = "bookmarks_screen",
        title = "Bookmarks",
        icon = Icons.Sharp.Bookmarks
    )

    object Settings : BottomNavScreens(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Sharp.Settings
    )

}
