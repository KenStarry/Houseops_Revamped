package com.example.houseops_revamped.navigation.graphs.admin_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseops_revamped.feature_admin.feature_agents.AdminAgentsScreen
import com.example.houseops_revamped.feature_admin.feature_home.AdminHomeScreen
import com.example.houseops_revamped.feature_admin.feature_settings.AdminSettingsScreen
import com.example.houseops_revamped.navigation.screens.AdminBottomNavScreens

@Composable
fun AdminBottomNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = AdminBottomNavScreens.Home.route
    ) {

        composable(route = AdminBottomNavScreens.Home.route) {
            AdminHomeScreen(navHostController = navHostController)
        }

        composable(route = AdminBottomNavScreens.Agents.route) {
            AdminAgentsScreen(navHostController = navHostController)
        }

        composable(route = AdminBottomNavScreens.Settings.route) {
            AdminSettingsScreen(navHostController = navHostController)
        }
    }

}


















