package com.example.houseops_revamped.navigation.screens

import com.example.houseops_revamped.navigation.NavConstants

sealed class AgentScreens(
    val route: String
) {

    object Main : AgentScreens(route = NavConstants.AGENT_MAIN_ROUTE)
}
