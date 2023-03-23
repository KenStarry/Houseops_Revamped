package com.kenstarry.houseops_revamped.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.BubbleChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector
import com.kenstarry.houseops_revamped.navigation.NavConstants

sealed class LandlordBottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_HOME_SCREEN_ROUTE,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Statistics : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_STATS_SCREEN_ROUTE,
        title = "Statistics",
        icon = Icons.Outlined.BubbleChart
    )

    object Agents : LandlordBottomNavScreens(
        route = NavConstants.LANDLORD_AGENTS_SCREEN_ROUTE,
        title = "Agents",
        icon = Icons.Outlined.SupportAgent
    )
}
