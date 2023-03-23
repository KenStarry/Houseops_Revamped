package com.kenstarry.houseops_revamped.navigation.graphs.landlord_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kenstarry.houseops_revamped.feature_landlord.feature_caretakers.presentation.LandlordAgents
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.LandlordAddApartment
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.LandlordHome
import com.kenstarry.houseops_revamped.feature_landlord.feature_stats.presentation.LandlordStats
import com.kenstarry.houseops_revamped.navigation.screens.LandlordBottomNavScreens
import com.kenstarry.houseops_revamped.navigation.screens.LandlordScreens

@Composable
fun LandlordBottomNavGraph(
    navHostController: NavHostController,
    mainNavHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = LandlordBottomNavScreens.Home.route
    ) {

        composable(route = LandlordBottomNavScreens.Home.route) {
            LandlordHome(
                mainNavHostController = mainNavHostController,
                navHostController = navHostController
            )
        }

        composable(route = LandlordBottomNavScreens.Statistics.route) {
            LandlordStats(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Agents.route) {
            LandlordAgents(navHostController = navHostController)
        }

        //  landlord add apartment screen
        composable(route = LandlordScreens.AddApartment.route) {
            LandlordAddApartment(navHostController = navHostController)
        }
    }

}