package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.models.Constants.SPLASH_ROUTE
import com.example.houseops_revamped.navigation.Screens
import com.example.houseops_revamped.screens.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.Splash.route,
        route = SPLASH_ROUTE
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
    }

}