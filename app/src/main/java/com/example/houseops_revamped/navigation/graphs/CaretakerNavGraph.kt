package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.models.Constants
import com.example.houseops_revamped.navigation.Screens
import com.example.houseops_revamped.screens.CaretakerRegistrationScreen

fun NavGraphBuilder.caretakerNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.CaretakerRegistration.route,
        route = Constants.CARETAKER_ROUTE
    ) {

        composable(route = Screens.CaretakerRegistration.route) {
            CaretakerRegistrationScreen(navHostController)
        }
    }
}