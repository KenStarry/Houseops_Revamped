package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_landlord.feature_main.presentation.LandlordMain
import com.example.houseops_revamped.navigation.LandlordScreens

fun NavGraphBuilder.landlordNavGraph(
    navHostController: NavHostController
) {

    navigation(
        startDestination = LandlordScreens.Main.route,
        route = Constants.LANDLORD_ROUTE
    ) {

        //  landlord home screen
        composable(route = LandlordScreens.Main.route) {
            LandlordMain(navHostController = navHostController)
        }
    }

}