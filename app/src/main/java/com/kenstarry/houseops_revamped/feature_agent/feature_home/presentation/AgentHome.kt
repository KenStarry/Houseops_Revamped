package com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.canopas.lib.showcase.ShowcaseStyle
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.components.ErrorLottie
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.model.AgentHomeEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeApartments
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeFab
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeTopBar
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.intro_showcase.QuickAddShowCase
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.viewmodel.AgentHomeViewModel
import com.kenstarry.houseops_revamped.navigation.Direction
import com.kenstarry.houseops_revamped.navigation.screens.AgentScreens
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeHeader as AgentHomeHeader1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentHome(
    mainNavHostController: NavHostController,
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val agentHomeVM: AgentHomeViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val directionInner = Direction(navHostController)
    val context = LocalContext.current
    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    agentHomeVM.onEvent(
        AgentHomeEvents.GetAgentApartments(
            email = userDetails?.userEmail ?: "no email",
            onResponse = {
                when (it) {
                    is Response.Success -> {}
                    is Response.Failure -> {
                        Toast.makeText(context, "something went wrong...", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        ))

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
                    userDetails = userDetails,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onBackPressed = {
                        direction.navigateUp()
                    }
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

                    //  agent header
                    AgentHomeHeader1(
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    //  agent apartments
                    if (agentHomeVM.agentApartments.value.isNotEmpty()) {
                        AgentHomeApartments(
                            apartments = agentHomeVM.agentApartments.value,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onApartmentClicked = {
                                directionInner.navigateToRoute(
                                    AgentScreens.ApartmentView.passApartmentName(it.apartmentName),
                                    null
                                )
                            }
                        )
                    } else {
                        ErrorLottie(
                            lottieImage = R.raw.search_empty,
                            title = null,
                            message = "No Apartments Assigned to you yet..."
                        )
                    }

                }

            }

        }
    }

}