package com.example.houseops_revamped.feature_admin.feature_main.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.components.EmailVerificationMessage
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_admin.feature_home.presentation.components.AdminHomeContent
import com.example.houseops_revamped.feature_admin.feature_home.presentation.components.bottomsheets.AdminVerificationSheet
import com.example.houseops_revamped.feature_admin.feature_home.presentation.utils.AdminConstants
import com.example.houseops_revamped.feature_admin.feature_main.presentation.components.bottom_nav.AdminBottomNav
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.navigation.graphs.admin_graphs.AdminBottomNavGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AdminMainScreen(
    navHostController: NavHostController
) {

    val navController = rememberNavController()
    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    BottomSheet(
        sheetBackground = MaterialTheme.colorScheme.onPrimary,
        sheetContent = { state, scope ->

            Log.d("sheet", "sheet - ${coreVM.bottomSheetContent.value}")

            when (coreVM.bottomSheetContent.value) {

                AdminConstants.BOTTOM_SHEET_VERIFICATION_SUCCESS -> {
                    AdminVerificationSheet(
                        coreViewModel = coreVM,
                        onLogout = {
                            direction.navigateToRoute(
                                Constants.AUTHENTICATION_ROUTE,
                                Constants.ADMIN_ROUTE
                            )
                        }
                    )
                }
            }

        },
        sheetScope = { state, scope ->

            Scaffold(
                bottomBar = {
                    AdminBottomNav(navHostController = navController)
                }
            ) { contentPadding ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(contentPadding)
                ) {
                    AdminBottomNavGraph(
                        navHostController = navController,
                        coreViewModel = coreVM,
                        modalSheetState = state,
                        scope = scope
                    )
                }

            }

        },
        closeBottomSheet = { state, scope ->
            coreVM.onBottomSheetEvent(BottomSheetEvents.CloseBottomSheet(state, scope))
        }
    )

}