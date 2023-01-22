package com.example.houseops_revamped.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseops_revamped.core.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.utils.Constants.ROOT_ROUTE

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE
    ) {

        authNavGraph(navHostController = navHostController)
        homeNavGraph(navHostController = navHostController)
        caretakerNavGraph(navHostController = navHostController)
    }

}