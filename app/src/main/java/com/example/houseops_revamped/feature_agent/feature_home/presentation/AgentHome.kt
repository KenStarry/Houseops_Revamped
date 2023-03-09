package com.example.houseops_revamped.feature_agent.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.canopas.lib.showcase.ShowcaseStyle
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeFab
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeHeader
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeTopBar
import com.example.houseops_revamped.feature_agent.feature_home.presentation.components.intro_showcase.QuickAddShowCase
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentHome(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current
    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {
        Scaffold(
            topBar = {
                AgentHomeTopBar(
                    context = context,
                    userDetails = userDetails
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
                            content = {
                                QuickAddShowCase(primaryColor = primaryColor)
                            }
                        )
                )
            }
        ) { contentPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(contentPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    //  agent pill
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HomePillBtns(
                            icon = Icons.Outlined.SupportAgent,
                            title = userDetails?.userType ?: "",
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onClick = {}
                        )

                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    //  agent header
                    AgentHomeHeader(
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )

                }

            }

        }
    }

}