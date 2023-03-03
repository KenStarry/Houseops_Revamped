package com.example.houseops_revamped.feature_admin.feature_main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.feature_admin.feature_main.presentation.components.bottom_nav.AdminBottomNav
import com.example.houseops_revamped.navigation.graphs.admin_graphs.AdminBottomNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMainScreen(
    navHostController: NavHostController
) {

    val navController = rememberNavController()

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
            AdminBottomNavGraph(navHostController = navController)
        }

    }

}