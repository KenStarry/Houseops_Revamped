package com.example.houseops_revamped.feature_landlord.feature_main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.navigation.graphs.LandlordBottomNavGraph
import com.example.houseopscaretakers.feature_landlord.feature_main.presentation.components.bottom_nav.MainBottomNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordMain(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    //  declare another navController for the sake of the nested navgraph
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            MainBottomNav(
                navHostController = navController
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {
            
            LandlordBottomNavGraph(navHostController = navController)

        }

    }

}