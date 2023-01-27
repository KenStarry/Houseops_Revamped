package com.example.houseops_revamped.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Bookmarks
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.sharp.Timelapse
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
) {

    object Home : BottomNavScreens(
        route = "home_screen",
        title = "Home",
        icon = Icons.Sharp.Home
    )

    object Booked : BottomNavScreens(
        route = "booked_screen",
        title = "Booked",
        icon = Icons.Sharp.Timelapse,
        badgeCount = 5
    )

    object Bookmarks : BottomNavScreens(
        route = "bookmarks_screen",
        title = "Bookmarks",
        icon = Icons.Sharp.Bookmarks,
        badgeCount = 13
    )

    object Settings : BottomNavScreens(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Sharp.Settings
    )

}
