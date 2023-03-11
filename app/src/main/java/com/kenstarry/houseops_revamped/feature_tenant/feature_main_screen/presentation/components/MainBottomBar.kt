package com.kenstarry.houseops_revamped.feature_tenant.feature_main_screen.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kenstarry.houseops_revamped.navigation.screens.BottomNavScreens

//  navigation bar
@Composable
fun MainBottomBar(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color,
) {

    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Booked,
        BottomNavScreens.Bookmarks,
        BottomNavScreens.Settings,
    )

    //  current destination
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //  check if any of the screens has a route that's not at home
    val bottombarDestination = screens.any { it.route == currentDestination?.route }
    val bottombarVisibilityState = remember {
        mutableStateOf(true)
    }

    bottombarVisibilityState.value = bottombarDestination

    //  animate the visibility
    AnimatedVisibility(
        visible = bottombarVisibilityState.value,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 300
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 300
            )
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {
            screens.forEach { screen ->
                MainBottomBarItem(
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