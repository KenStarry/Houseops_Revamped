package com.kenstarry.houseops_revamped.feature_admin.feature_main.presentation.components.bottom_nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.navigation.screens.AdminBottomNavScreens
import com.kenstarry.houseops_revamped.navigation.screens.LandlordBottomNavScreens

@Composable
fun RowScope.AdminBottomNavItem(
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    screen: AdminBottomNavScreens,
    primaryColor: Color,
    tertiaryColor: Color
) {

    NavigationBarItem(

        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        alwaysShowLabel = true,

        label = {
            Text(text = screen.title)
        },

        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            indicatorColor =  tertiaryColor
        ),

        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Bottom bar icon"
            )
        },

        onClick = {
            navHostController.navigate(route = screen.route) {
                popUpTo(LandlordBottomNavScreens.Home.route)
                launchSingleTop = true
            }
        }
    )

}






















