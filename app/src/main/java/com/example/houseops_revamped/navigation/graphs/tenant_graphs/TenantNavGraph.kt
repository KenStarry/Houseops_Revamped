package com.example.houseops_revamped.navigation.graphs.tenant_graphs

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
        startDestination = Screens.Main.route,
        route = HOME_ROUTE
    ) {

        //  main screen
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navHostController)
        }
    }
}