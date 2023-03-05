package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.presentation.components.BackPressTopAppBar
import com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.components.AdminLandlordViewAppBar
import com.example.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLandlordView(
    navHostController: NavHostController,
    landlordEmail: String
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current
    val landlord = coreVM.getUserDetails(landlordEmail)

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

    Scaffold(
        topBar = {
            AdminLandlordViewAppBar(
                title = landlord?.userName ?: "",
                email = landlordEmail,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onBackPressed = {
                    direction.navigateUp()
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

        }
    }

}