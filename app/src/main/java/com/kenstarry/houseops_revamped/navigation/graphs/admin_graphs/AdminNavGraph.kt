package com.kenstarry.houseops_revamped.navigation.graphs.admin_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_admin.feature_main.presentation.AdminMainScreen
import com.kenstarry.houseops_revamped.navigation.screens.AdminScreens

fun NavGraphBuilder.adminNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = AdminScreens.Main.route,
        route = Constants.ADMIN_ROUTE
    ) {

        //  landlord home screen
        composable(route = AdminScreens.Main.route) {
            AdminMainScreen(navHostController = navHostController)
        }
    }

}