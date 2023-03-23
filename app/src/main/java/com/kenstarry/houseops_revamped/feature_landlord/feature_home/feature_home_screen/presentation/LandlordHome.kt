package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.presentation.components.ExtendedFab
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model.LndHomeEvents
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components.LndHomeApartments
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components.LndHomeTopBar
import com.kenstarry.houseops_revamped.core.presentation.components.ErrorLottie
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel
import com.kenstarry.houseops_revamped.navigation.Direction
import com.kenstarry.houseops_revamped.navigation.screens.LandlordScreens
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordHome(
    mainNavHostController: NavHostController,
    navHostController: NavHostController,
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lndHomeVM: LndHomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val directionInner = Direction(navHostController)
    val direction = Direction(mainNavHostController)

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

    lndHomeVM.onEvent(
        LndHomeEvents.GetLandlordDetails(
            email = coreVM.currentUser()?.email ?: "no user",
            response = {}
        )
    )

    lndHomeVM.onEvent(
        LndHomeEvents.GetLandlordApartments(
            email = lndHomeVM.landlordDetails.value?.userEmail ?: "no email",
            response = {}
        )
    )
    val landlord = lndHomeVM.landlordDetails.value

    val currentHour by remember {
        mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY))
    }
    var greetingsText by remember { mutableStateOf("") }
    var greetingsIcon by remember { mutableStateOf(Icons.Outlined.ModeNight) }

    lndHomeVM.onEvent(
        LndHomeEvents.FilterGreetingsText(
            currentHour = currentHour,
            greetings = { text, icon ->
                greetingsText = text
                greetingsIcon = icon
            }
        ))

    landlord?.let {
        if (it.userIsVerified) {

            //  show main UI
            Scaffold(
                topBar = {
                    LndHomeTopBar(
                        context = context,
                        userDetails = landlord,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        onBackPressed = {
                            direction.navigateUp()
                        }
                    )
                },
                floatingActionButton = {
                    ExtendedFab(
                        icon = Icons.Outlined.Apartment,
                        title = "Add Apartment",
                        containerColor = primaryColor,
                        onFabClicked = {
                            directionInner.navigateToRoute(LandlordScreens.AddApartment.route, null)
                        }
                    )
                },
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {

                        //  apartments section
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            if (lndHomeVM.landlordApartments.value.isEmpty()) {
                                Lottie(
                                    rawFile = R.raw.paperplane,
                                    isPlaying = true,
                                    iterations = Int.MAX_VALUE,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.5f)
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Add Apartments to see them here.",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )
                            } else {

                                //  apartments section
                                LndHomeApartments(
                                    lndHomeVM = lndHomeVM,
                                    primaryColor = primaryColor,
                                    tertiaryColor = tertiaryColor
                                )
                            }

                        }

                    }
                }

            }

        } else {
            //  show an error message
            ErrorLottie(
                lottieImage = R.raw.email_verification,
                title = "Verification Pending",
                message = "We are working on verifying you..."
            )
        }
    }

}
























