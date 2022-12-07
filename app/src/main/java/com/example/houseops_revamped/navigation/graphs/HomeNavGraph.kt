package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.navigation.BottomNavScreens
import com.example.houseops_revamped.navigation.HOME_ROUTE
import com.example.houseops_revamped.navigation.Screens
import com.example.houseops_revamped.screens.MainScreen
import com.example.houseops_revamped.screens.SplashScreen
import com.example.houseops_revamped.screens.bottom_nav_screens.BookedScreen
import com.example.houseops_revamped.screens.bottom_nav_screens.HomeScreen
import com.example.houseops_revamped.screens.bottom_nav_screens.SettingsScreen
import com.example.houseops_revamped.screens.bottom_nav_screens.WishlistScreen

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {

    //  home screens
    navigation(
        startDestination = BottomNavScreens.Home.route,
        route = HOME_ROUTE
    ) {

        //  main screen
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navHostController)
        }

        //  Home screen
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen(navHostController)
        }

        //  Booked screen
        composable(route = BottomNavScreens.Booked.route) {
            BookedScreen()
        }

        //  Wishlist screen
        composable(route = BottomNavScreens.Wishlist.route) {
            WishlistScreen()
        }

        //  Settings screen
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen(navHostController)
        }
    }
}