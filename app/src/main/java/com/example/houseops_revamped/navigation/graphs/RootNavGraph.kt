package com.example.houseops_revamped.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.ROOT_ROUTE

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) HOME_ROUTE else AUTHENTICATION_ROUTE,
        route = ROOT_ROUTE
    ) {

        authNavGraph(navHostController = navHostController)
        homeNavGraph(navHostController = navHostController)
    }

}