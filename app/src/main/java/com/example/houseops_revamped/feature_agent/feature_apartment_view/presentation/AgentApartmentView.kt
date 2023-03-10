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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.canopas.lib.showcase.ShowcaseStyle
import com.canopas.lib.showcase.introShowCaseTarget
import com.canopas.lib.showcase.rememberIntroShowCaseState
import com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.AgentApartmentTopBar
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeFab
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.intro_showcase.QuickAddShowCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentApartmentView(
    navHostController: NavHostController,
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        Scaffold(
            topBar = {
                AgentApartmentTopBar(
                    title = apartmentName,
                    onBackPressed = { /*TODO*/ },
                    scrollBehavior = scrollBehavior
                )
            },

            floatingActionButton = {
                AgentHomeFab(
                    primaryColor = primaryColor,
                    onClick = {},
                    modifier = Modifier
                        .introShowCaseTarget(
                            index = 0,
                            style = ShowcaseStyle.Default.copy(
                                backgroundColor = tertiaryColor, // specify color of background
                                backgroundAlpha = 0.8f, // specify transparency of background
                                targetCircleColor = Color.White.copy(alpha = 0.3f) // specify color of target circle
                            ),
                            content = {}
                        )
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

}