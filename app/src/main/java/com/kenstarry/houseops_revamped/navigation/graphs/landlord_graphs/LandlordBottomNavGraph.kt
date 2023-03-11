package com.kenstarry.houseops_revamped.navigation.graphs.landlord_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.houseopscaretakers.feature_landlord.feature_caretakers.LandlordCaretakers
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.LandlordAddApartment
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.LandlordHome
import com.kenstarry.houseops_revamped.feature_landlord.feature_settings.presentation.LandlordSettings
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
            LandlordHome(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Caretakers.route) {
            LandlordCaretakers(navHostController = navHostController)
        }

        composable(route = LandlordBottomNavScreens.Settings.route) {
            LandlordSettings(navHostController = mainNavHostController)
        }

        //  landlord add apartment screen
        composable(route = LandlordScreens.AddApartment.route) {
            LandlordAddApartment(navHostController = navHostController)
        }
    }

}