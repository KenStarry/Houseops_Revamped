package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.navigation.Screens
import com.example.houseops_revamped.screens.CaretakerRegistrationScreen

fun NavGraphBuilder.caretakerNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.CaretakerRegistration.route,
        route = Constants.CARETAKER_ROUTE
    ) {

        //  caretaker registration screen
        composable(
            route = Screens.CaretakerRegistration.route,
            arguments = listOf(
                navArgument(Constants.CARETAKER_HAS_REGISTERED) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            CaretakerRegistrationScreen(
                navHostController,
                it.arguments?.getBoolean(Constants.CARETAKER_HAS_REGISTERED)!!
            )
        }
    }
}