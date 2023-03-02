package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.core.presentation.LoadingScreen
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.navigation.Screens

fun NavGraphBuilder.loadingNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screens.Loading.route,
        route = Constants.LOADING_ROUTE
    ) {
        //  loading screen
        composable(route = Screens.Loading.route) {
            LoadingScreen()
        }
    }
}