package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_admin.feature_home.domain.models.AdminHomeEvents
import com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.viewmodel.AdminHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeContent(
    onActionsClicked: (landlord: UsersCollection) -> Unit,
    onCardClicked: (landlord: UsersCollection) -> Unit,
    onBackPressed: () -> Unit
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val adminHomeVM: AdminHomeViewModel = hiltViewModel()
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
    val userDetails: UsersCollection? = coreVM.getUserDetails(
        currentUser?.email ?: "no email"
    )

    //  get all landlords
    adminHomeVM.onEvent(
        AdminHomeEvents.GetLandlords(
        response = {}
    ))

    Scaffold(
        topBar = {
            AdminHomeAppBar(
                context = context,
                userName = if (userDetails?.userName == "no name")
                    currentUser?.displayName ?: ""
                else
                    userDetails?.userName ?: "",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onBackPressed = onBackPressed
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  title scren
                AdminHomeHeader(
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

                //  show all landlords registered by the app
                AdminViewLandlords(
                    adminHomeVM = adminHomeVM,
                    currentUser = currentUser,
                    context = context,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onCardClicked = { onCardClicked(it) },
                    onActionsClicked = { onActionsClicked(it) }
                )

            }

        }

    }
}