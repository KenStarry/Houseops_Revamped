package com.kenstarry.houseops_revamped.navigation.graphs.tenant_graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.BookedScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.BookmarkScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.CategoriesScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.dashboard_screen.presentation.DashboardScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.HomeScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.HouseViewScreen
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.SettingsScreen
import com.kenstarry.houseops_revamped.navigation.screens.BottomNavScreens
import com.kenstarry.houseops_revamped.navigation.screens.Screens

@Composable
fun TenantInnerGraph(
    mainNavHostController: NavHostController,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomNavScreens.Home.route
    ) {

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
            SettingsScreen(mainNavHostController, navHostController)
        }

        //  Dashboard Screen
        composable(route = Screens.Dashboard.route) {
            DashboardScreen(navHostController = navHostController)
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