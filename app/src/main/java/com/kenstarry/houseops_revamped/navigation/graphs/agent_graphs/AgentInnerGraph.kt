package com.kenstarry.houseops_revamped.navigation.graphs.agent_graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.AgentApartmentView
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.AgentHome
import com.kenstarry.houseops_revamped.feature_agent.feature_notifications.AgentNotifications
import com.kenstarry.houseops_revamped.feature_agent.feature_overview.AgentOverview
import com.kenstarry.houseops_revamped.navigation.screens.AgentBottomNavScreens
import com.kenstarry.houseops_revamped.navigation.screens.AgentScreens

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
            AgentHome(mainNavHostController, navHostController, primaryColor, tertiaryColor)
        }

        composable(route = AgentBottomNavScreens.Notifications.route) {
            AgentNotifications(navHostController, primaryColor, tertiaryColor)
        }

        composable(route = AgentBottomNavScreens.Overview.route) {
            AgentOverview(navHostController, primaryColor, tertiaryColor)
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
                apartmentName = it.arguments?.getString("apartmentName") ?: "",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }

    }

}