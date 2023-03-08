package com.example.houseops_revamped.navigation.graphs.agent_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_agent.feature_main.presentation.AgentMainScreen
import com.example.houseops_revamped.feature_landlord.feature_main.presentation.LandlordMain
import com.example.houseops_revamped.navigation.screens.AgentScreens
import com.example.houseops_revamped.navigation.screens.LandlordScreens

fun NavGraphBuilder.agentNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = AgentScreens.Main.route,
        route = Constants.AGENT_ROUTE
    ) {

        //  landlord main screen
        composable(route = AgentScreens.Main.route) {
            AgentMainScreen(navHostController = navHostController)
        }
    }
}