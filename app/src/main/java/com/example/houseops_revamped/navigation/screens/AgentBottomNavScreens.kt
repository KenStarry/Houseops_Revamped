package com.example.houseops_revamped.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.houseops_revamped.navigation.NavConstants

sealed class AgentBottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : AgentBottomNavScreens(
        route = NavConstants.AGENT_HOME_SCREEN_ROUTE,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Notifications : AgentBottomNavScreens(
        route = NavConstants.AGENT_NOTIFICATIONS_SCREEN_ROUTE,
        title = "Notifications",
        icon = Icons.Outlined.Notifications
    )

    object Overview : AgentBottomNavScreens(
        route = NavConstants.AGENT_OVERVIEW_SCREEN_ROUTE,
        title = "Overview",
        icon = Icons.Outlined.Dashboard
    )

    object Settings : AgentBottomNavScreens(
        route = NavConstants.AGENT_SETTINGS_SCREEN_ROUTE,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
