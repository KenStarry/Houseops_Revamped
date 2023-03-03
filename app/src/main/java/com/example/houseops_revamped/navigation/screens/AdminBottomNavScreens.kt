package com.example.houseops_revamped.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.houseops_revamped.navigation.NavConstants

sealed class AdminBottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : AdminBottomNavScreens(
        route = NavConstants.ADMIN_BOTTOM_NAV_HOME_ROUTE,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Agents : AdminBottomNavScreens(
        route = NavConstants.ADMIN_BOTTOM_NAV_AGENTS_ROUTE,
        title = "Agents",
        icon = Icons.Outlined.SupportAgent
    )

    object Settings : AdminBottomNavScreens(
        route = NavConstants.ADMIN_BOTTOM_NAV_SETTINGS_ROUTE,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
