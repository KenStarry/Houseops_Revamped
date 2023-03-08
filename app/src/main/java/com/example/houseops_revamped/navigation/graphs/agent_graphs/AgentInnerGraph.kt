package com.example.houseops_revamped.navigation.graphs.agent_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseops_revamped.feature_agent.feature_home.presentation.AgentHome
import com.example.houseops_revamped.feature_agent.feature_notifications.AgentNotifications
import com.example.houseops_revamped.feature_agent.feature_overview.AgentOverview
import com.example.houseops_revamped.feature_agent.feature_settings.AgentSettings
import com.example.houseops_revamped.navigation.screens.AgentBottomNavScreens

@Composable
fun AgentInnerGraph(
    navHostController: NavHostController,
    mainNavHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = AgentBottomNavScreens.Home.route
    ) {

        composable(route = AgentBottomNavScreens.Home.route) {
            AgentHome(navHostController)
        }

        composable(route = AgentBottomNavScreens.Notifications.route) {
            AgentNotifications(navHostController)
        }

        composable(route = AgentBottomNavScreens.Overview.route) {
            AgentOverview(navHostController)
        }

        composable(route = AgentBottomNavScreens.Settings.route) {
            AgentSettings(mainNavHostController)
        }

    }

}