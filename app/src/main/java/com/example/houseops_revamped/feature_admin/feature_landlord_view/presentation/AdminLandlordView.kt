package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.presentation.components.BackPressTopAppBar
import com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.components.AdminLandlordApartmentsPreview
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                            .background(tertiaryColor),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Apartment,
                            contentDescription = "Apartment icon",
                            tint = primaryColor
                        )
                    }

                    Text(
                        text = "Apartments",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                }

                //  apartments preview
                AdminLandlordApartmentsPreview(
                    landlordEmail = landlordEmail
                )

            }

        }
    }

}