package com.kenstarry.houseops_revamped.feature_tenant.feature_home.dashboard_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.presentation.components.ComingSoon

@Composable
fun DashboardScreen(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {

        ComingSoon(
            modifier = Modifier
                .fillMaxSize(),
            title = "Dashboard"
        )

    }
}