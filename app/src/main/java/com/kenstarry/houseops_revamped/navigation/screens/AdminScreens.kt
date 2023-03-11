package com.kenstarry.houseops_revamped.navigation.screens

import com.kenstarry.houseops_revamped.navigation.NavConstants

sealed class AdminScreens(
    val route: String
) {

    object Main : AdminScreens(route = NavConstants.ADMIN_MAIN_ROUTE)

    object LandlordView :
        AdminScreens(route = "${NavConstants.ADMIN_LANDLORD_VIEW_ROUTE}/{landlordEmail}") {
        fun passLandlordEmail(landlordEmail: String) =
            "${NavConstants.ADMIN_LANDLORD_VIEW_ROUTE}/$landlordEmail"
    }
}
