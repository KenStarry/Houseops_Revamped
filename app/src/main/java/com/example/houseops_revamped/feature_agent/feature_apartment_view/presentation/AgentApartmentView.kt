package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.AgentApartmentTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentApartmentView(
    navHostController: NavHostController,
    apartmentName: String
) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            AgentApartmentTopBar(
                title = apartmentName,
                onBackPressed = { /*TODO*/ },
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

            }

        }

    }

}