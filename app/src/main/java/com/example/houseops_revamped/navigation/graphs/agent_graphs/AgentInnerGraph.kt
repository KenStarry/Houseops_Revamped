package com.example.houseops_revamped.navigation.graphs.agent_graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
    mainNavHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {

    NavHost(
        navController = navHostController,
        startDestination = AgentBottomNavScreens.Home.route
    ) {

        composable(route = AgentBottomNavScreens.Home.route) {
            AgentHome(navHostController, primaryColor, tertiaryColor)
        }

        composable(route = AgentBottomNavScreens.Notifications.route) {
            AgentNotifications(navHostController, primaryColor, tertiaryColor)
        }

        composable(route = AgentBottomNavScreens.Overview.route) {
            AgentOverview(navHostController, primaryColor, tertiaryColor)
        }

        composable(route = AgentBottomNavScreens.Settings.route) {
            AgentSettings(mainNavHostController, primaryColor, tertiaryColor)
        }

    }

}