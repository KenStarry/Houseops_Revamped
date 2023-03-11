package com.kenstarry.houseops_revamped.navigation.graphs.landlord_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_landlord.feature_main.presentation.LandlordMain
import com.kenstarry.houseops_revamped.navigation.screens.LandlordScreens

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