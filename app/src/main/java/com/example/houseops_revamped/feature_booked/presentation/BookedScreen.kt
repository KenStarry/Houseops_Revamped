package com.example.houseops_revamped.feature_booked.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_booked.domain.model.BookedEvents
import com.example.houseops_revamped.feature_booked.presentation.components.BookedAppBar
import com.example.houseops_revamped.feature_booked.presentation.components.booked_houses.BookedHouses
import com.example.houseops_revamped.feature_booked.presentation.viewmodel.BookedViewModel
import com.example.houseops_revamped.feature_bookmark.presentation.components.BookmarkCategories
import com.example.houseops_revamped.feature_home.home_screen.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val bookedVM: BookedViewModel = hiltViewModel()
    val context = LocalContext.current

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    Scaffold(
        topBar = {
            BookedAppBar(
                onBackPressed = {
                    //  go back to home screen
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            //  booked houses list
            userDetails?.userBookedHouses?.let {
                BookedHouses(
                    context = context,
                    user = userDetails,
                    bookedHouses = it,
                    modifier = Modifier
                        .fillMaxSize(),
                    bookedVm = bookedVM
                )
            }

        }

    }
}