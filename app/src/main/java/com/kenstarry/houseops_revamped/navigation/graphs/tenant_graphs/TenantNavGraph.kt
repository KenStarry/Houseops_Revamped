package com.kenstarry.houseops_revamped.navigation.graphs.tenant_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.kenstarry.houseops_revamped.feature_tenant.feature_main_screen.presentation.MainScreen
import com.kenstarry.houseops_revamped.navigation.screens.Screens

fun NavGraphBuilder.tenantNavGraph(
    navHostController: NavHostController
) {

    //  home screens
    navigation(
        startDestination = Screens.Main.route,
        route = HOME_ROUTE
    ) {

        //  main screen
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navHostController)
        }
    }
}