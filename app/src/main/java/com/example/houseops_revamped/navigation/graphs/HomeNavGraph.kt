package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.feature_tenant.feature_categories.CategoriesScreen
import com.example.houseops_revamped.feature_tenant.feature_booked.presentation.BookedScreen
import com.example.houseops_revamped.feature_tenant.feature_bookmark.BookmarkScreen
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.HomeScreen
import com.example.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.HouseViewScreen
import com.example.houseops_revamped.feature_tenant.feature_main_screen.presentation.MainScreen
import com.example.houseops_revamped.feature_tenant.feature_settings.SettingsScreen
import com.example.houseops_revamped.navigation.screens.BottomNavScreens
import com.example.houseops_revamped.navigation.screens.Screens

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
            BookedScreen(navHostController)
        }

        //  Wishlist screen
        composable(route = BottomNavScreens.Bookmarks.route) {
            BookmarkScreen(navHostController = navHostController)
        }

        //  Settings screen
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen(navHostController)
        }

        //  categories screen
        composable(
            route = Screens.Categories.route,
            arguments = listOf(
                navArgument("categoryTitle") {
                    type = NavType.StringType
                }
            )
        ) {
            CategoriesScreen(
                navHostController = navHostController,
                categoryTitle = it.arguments?.getString("categoryTitle") ?: "none"
            )
        }

        //  Home View Screen
        composable(
            route = Screens.HouseView.route,
            arguments = listOf(
                navArgument("apartment") {
                    type = NavType.StringType
                },
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            HouseViewScreen(
                navHostController = navHostController,
                apartment = it.arguments?.getString("apartment") ?: "none",
                category = it.arguments?.getString("category") ?: "none"
            )
        }
    }
}