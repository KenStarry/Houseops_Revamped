package com.example.houseops_revamped.navigation.graphs.agent_graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.AgentApartmentView
import com.example.houseops_revamped.feature_agent.feature_home.presentation.AgentHome
import com.example.houseops_revamped.feature_agent.feature_notifications.AgentNotifications
import com.example.houseops_revamped.feature_agent.feature_overview.AgentOverview
import com.example.houseops_revamped.feature_agent.feature_settings.AgentSettings
import com.example.houseops_revamped.navigation.screens.AgentBottomNavScreens
import com.example.houseops_revamped.navigation.screens.AgentScreens

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

        composable(
            route = AgentScreens.ApartmentView.route,
            arguments = listOf(
                navArgument("apartmentName") {
                    type = NavType.StringType
                }
            )
        ) {
            AgentApartmentView(
                navHostController = navHostController,
                apartmentName = it.arguments?.getString("apartmentName") ?: ""
            )
        }

    }

}