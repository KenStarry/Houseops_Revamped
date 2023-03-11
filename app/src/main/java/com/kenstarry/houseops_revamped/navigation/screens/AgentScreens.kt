package com.kenstarry.houseops_revamped.navigation.screens

import com.kenstarry.houseops_revamped.navigation.NavConstants

sealed class AgentScreens(
    val route: String
) {
    object Main : AgentScreens(route = NavConstants.AGENT_MAIN_ROUTE)

    object ApartmentView :
        AgentScreens(route = "${NavConstants.AGENT_APARTMENT_VIEW_SCREEN_ROUTE}/{apartmentName}") {

        fun passApartmentName(apartmentName: String) =
            "${NavConstants.AGENT_APARTMENT_VIEW_SCREEN_ROUTE}/$apartmentName"
    }
}
