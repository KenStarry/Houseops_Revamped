package com.kenstarry.houseops_revamped.feature_admin.feature_main.presentation.components.bottom_nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kenstarry.houseops_revamped.navigation.screens.AdminBottomNavScreens

@Composable
fun AdminBottomNav(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val screens = listOf(
        AdminBottomNavScreens.Home,
        AdminBottomNavScreens.Agents,
        AdminBottomNavScreens.Settings
    )

    //  current destination
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination =
        screens.any { it.route == currentDestination?.route }

    val isBottomBarVisible = remember {
        mutableStateOf(true)
    }

    isBottomBarVisible.value = bottomBarDestination

    //  show / hide bottombar depending on the screen
    AnimatedVisibility(visible = isBottomBarVisible.value) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {

            screens.forEach { screen ->
                AdminBottomNavItem(
                    navHostController = navHostController,
                    currentDestination = currentDestination,
                    screen = screen,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )
            }

        }
    }

}










































