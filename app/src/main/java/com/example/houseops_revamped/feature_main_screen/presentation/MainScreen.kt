package com.example.houseops_revamped.feature_main_screen.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_main_screen.presentation.components.MainBottomBar
import com.example.houseops_revamped.navigation.BottomNavScreens
import com.example.houseops_revamped.navigation.graphs.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            MainBottomBar(navHostController = navHostController)
        },
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            RootNavGraph(
                navHostController = navHostController,
                isLoggedIn = coreVM.isUserLoggedIn()
            )
        }
    }
}





































