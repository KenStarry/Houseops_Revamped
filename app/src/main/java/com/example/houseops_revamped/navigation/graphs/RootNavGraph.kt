package com.example.houseops_revamped.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseops_revamped.models.Constants.ROOT_ROUTE
import com.example.houseops_revamped.models.Constants.SPLASH_ROUTE

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = SPLASH_ROUTE,
        route = ROOT_ROUTE
    ) {

        authNavGraph(navHostController = navHostController)
        homeNavGraph(navHostController = navHostController)
        caretakerNavGraph(navHostController = navHostController)
        splashNavGraph(navHostController = navHostController)
    }

}