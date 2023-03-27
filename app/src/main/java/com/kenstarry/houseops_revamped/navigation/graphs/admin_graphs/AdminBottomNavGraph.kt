package com.kenstarry.houseops_revamped.navigation.graphs.admin_graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.presentation.AdminAgentsScreen
import com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.AdminHomeScreen
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.AdminLandlordView
import com.kenstarry.houseops_revamped.feature_admin.feature_settings.AdminSettingsScreen
import com.kenstarry.houseops_revamped.navigation.screens.AdminBottomNavScreens
import com.kenstarry.houseops_revamped.navigation.screens.AdminScreens
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
                mainNavHostController = mainNavHostController,
                navHostController = navHostController,
                coreViewModel = coreViewModel,
                modalSheetState = modalSheetState,
                scope = scope
            )
        }

        composable(route = AdminBottomNavScreens.Agents.route) {
            AdminAgentsScreen(navHostController = navHostController)
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


















