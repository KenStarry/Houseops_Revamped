package com.example.houseops_revamped.navigation.screens

import com.example.houseops_revamped.navigation.NavConstants

sealed class AdminScreens(
    val route: String
) {

    object Main : AdminScreens(route = NavConstants.ADMIN_MAIN_ROUTE)
}
