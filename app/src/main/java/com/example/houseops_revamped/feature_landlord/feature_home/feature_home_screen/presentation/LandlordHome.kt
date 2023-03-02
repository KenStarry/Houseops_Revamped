package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.Lottie
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model.LndHomeEvents
import com.example.houseops_revamped.navigation.Direction
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components.LndHomeApartments
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.components.LndHomeAppBar
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.presentation.components.LndHomeGreetings
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandlordHome(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val lndHomeVM: LndHomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val direction = Direction(navHostController)

    lndHomeVM.onEvent(
        LndHomeEvents.GetLandlordDetails(
            email = coreVM.currentUser()?.email ?: "no user"
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

    Scaffold(
        topBar = {
            LndHomeAppBar(
                context = context,
                imageUri = landlord?.userImageUri?.toUri()
            )
        },
        floatingActionButton = {
//            ExtendedFab(
//                icon = Icons.Outlined.Apartment,
//                text = "Add Apartment",
//                onClick = {
//                    direction.navigateToRoute(LandlordScreens.AddApartment.route)
//                }
//            )
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

                //  greetings text
                LndHomeGreetings(
                    landlordName = landlord?.userName ?: "",
                    greetingsText = greetingsText,
                    greetingsIcon = greetingsIcon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

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
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    } else {

                        //  apartments section
                        LndHomeApartments(
                            lndHomeVM = lndHomeVM
                        )
                    }

                }

            }
        }

    }

}
























