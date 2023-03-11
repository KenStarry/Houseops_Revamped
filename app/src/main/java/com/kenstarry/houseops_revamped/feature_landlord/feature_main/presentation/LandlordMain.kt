package com.kenstarry.houseops_revamped.feature_landlord.feature_main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_landlord.feature_main.presentation.components.bottom_nav.MainBottomNav
import com.kenstarry.houseops_revamped.navigation.graphs.landlord_graphs.LandlordBottomNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordMain(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    //  declare another navController for the sake of the nested navgraph
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            MainBottomNav(
                navHostController = navController,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            LandlordBottomNavGraph(
                navHostController = navController,
                mainNavHostController = navHostController
            )

        }

    }

}