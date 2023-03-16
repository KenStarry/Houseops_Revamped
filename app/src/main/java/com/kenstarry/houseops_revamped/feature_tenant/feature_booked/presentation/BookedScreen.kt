package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.components.BookedAppBar
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.components.booked_houses.BookedHouses
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.viewmodel.BookedViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val bookedVM: BookedViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current

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

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    Scaffold(
        topBar = {
            BookedAppBar(
                onBackPressed = {
                    //  go back to home screen
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {

            //  booked houses list
            userDetails?.userBookedHouses?.let {

                if (it.isNotEmpty()) {
                    BookedHouses(
                        context = context,
                        user = userDetails,
                        bookedHouses = it,
                        modifier = Modifier
                            .fillMaxSize(),
                        bookedVm = bookedVM,
                        direction = direction,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor
                    )
                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //  display lottie animation
                        Lottie(
                            rawFile = com.kenstarry.houseops_revamped.R.raw.paperplane,
                            isPlaying = true,
                            iterations = Integer.MAX_VALUE,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(250.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "No Booked Houses yet...",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )

                    }
                }
            }

        }

    }
}