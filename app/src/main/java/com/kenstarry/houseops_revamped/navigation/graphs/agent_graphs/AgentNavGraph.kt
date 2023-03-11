package com.kenstarry.houseops_revamped.navigation.graphs.agent_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_agent.feature_main.presentation.AgentMainScreen
import com.kenstarry.houseops_revamped.navigation.screens.AgentScreens

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