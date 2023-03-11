package com.kenstarry.houseops_revamped.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kenstarry.houseops_revamped.core.presentation.LoadingScreen
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.navigation.screens.Screens

fun NavGraphBuilder.loadingNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screens.Loading.route,
        route = Constants.LOADING_ROUTE
    ) {
        //  loading screen
        composable(
            route = Screens.Loading.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) {
            LoadingScreen(
                navHostController = navHostController,
                emailAddress = it.arguments?.getString("email")
            )
        }
    }
}