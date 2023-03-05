package com.example.houseops_revamped.navigation.graphs.admin_graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_admin.feature_agents.presentation.AdminAgentsScreen
import com.example.houseops_revamped.feature_admin.feature_home.presentation.AdminHomeScreen
import com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.AdminLandlordView
import com.example.houseops_revamped.feature_admin.feature_settings.presentation.AdminSettingsScreen
import com.example.houseops_revamped.navigation.screens.AdminBottomNavScreens
import com.example.houseops_revamped.navigation.screens.AdminScreens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminBottomNavGraph(
    navHostController: NavHostController,
    mainNavHostController: NavHostController,
    coreViewModel: CoreViewModel,
    modalSheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {

    NavHost(
        navController = navHostController,
        startDestination = AdminBottomNavScreens.Home.route
    ) {

        composable(route = AdminBottomNavScreens.Home.route) {
            AdminHomeScreen(
                navHostController = navHostController,
                coreViewModel = coreViewModel,
                modalSheetState = modalSheetState,
                scope = scope
            )
        }

        composable(route = AdminBottomNavScreens.Agents.route) {
            AdminAgentsScreen(navHostController = navHostController)
        }

        composable(route = AdminBottomNavScreens.Settings.route) {
            AdminSettingsScreen(navHostController = mainNavHostController)
        }

        //  landlord view screen
        composable(
            route = AdminScreens.LandlordView.route,
            arguments = listOf(
                navArgument("landlordEmail") {
                    type = NavType.StringType
                }
            )
        ) {
            AdminLandlordView(
                navHostController = navHostController,
                landlordEmail = it.arguments?.getString("landlordEmail") ?: "no data"
            )
        }
    }

}


















