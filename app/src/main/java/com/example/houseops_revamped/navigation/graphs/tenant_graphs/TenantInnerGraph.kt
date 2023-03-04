package com.example.houseops_revamped.navigation.graphs.tenant_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseops_revamped.feature_tenant.feature_booked.presentation.BookedScreen
import com.example.houseops_revamped.feature_tenant.feature_bookmark.BookmarkScreen
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.HomeScreen
import com.example.houseops_revamped.feature_tenant.feature_settings.SettingsScreen
import com.example.houseops_revamped.navigation.screens.BottomNavScreens

@Composable
fun TenantInnerGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomNavScreens.Home.route
    ) {

        //  Home screen
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen(navHostController)
        }

        //  Booked screen
        composable(route = BottomNavScreens.Booked.route) {
            BookedScreen(navHostController)
        }

        //  Wishlist screen
        composable(route = BottomNavScreens.Bookmarks.route) {
            BookmarkScreen(navHostController = navHostController)
        }

        //  Settings screen
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen(navHostController)
        }

    }

}