package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.ErrorLottie
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components.AdminLandlordApartmentsPreview
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components.AdminLandlordViewAppBar
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components.AssignDialog
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.utils.AdminLandlordConstants
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel.AdminLandlordViewVM
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLandlordView(
    navHostController: NavHostController,
    landlordEmail: String
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val landlordViewVM: AdminLandlordViewVM = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current
    val currentUser = coreVM.currentUser()
    val landlord = coreVM.getUserDetails(landlordEmail)

    landlordViewVM.onEvent(
        AdminLandlordViewEvents.GetApartments(
            landlordEmail = landlordEmail,
            response = {
                when (it) {
                    is Response.Success -> {}
                    is Response.Failure -> {}
                }
            }
        ))

    var clickedApartment by remember {
        mutableStateOf<Apartment?>(null)
    }

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
                title = if (landlord?.userName == "no name")
                    currentUser?.displayName ?: ""
                else
                    landlord?.userName ?: "",
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

            //  assign agent dialog
            AnimatedVisibility(visible = landlordViewVM.isAssignAlertDialogVisible.value) {
                AssignDialog(
                    apartment = clickedApartment,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onConfirm = { agent ->

                        Log.d("agent", "Selected Agent : ${agent?.userName}")

                        //  assign the agent to the apartment
                        coreVM.onEvent(
                            CoreEvents.UpdateFirestoreField(
                                collectionName = Constants.APARTMENTS_COLLECTION,
                                documentName = clickedApartment?.apartmentName ?: "",
                                subCollectionName = null,
                                subCollectionDocument = null,
                                fieldName = "apartmentAgentAssigned",
                                fieldValue = agent?.userEmail ?: "",
                                onResponse = { res ->
                                    when (res) {
                                        is Response.Success -> {
                                            Toast.makeText(
                                                context,
                                                "Agent assigned successfully!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        is Response.Failure -> {}
                                    }
                                }
                            ))

                        //  close Assign apartment dialog
                        landlordViewVM.onEvent(
                            AdminLandlordViewEvents.ToggleAlertDialog(
                                isVisible = false,
                                dialogType = AdminLandlordConstants.ASSIGN_AGENT_DIALOG
                            )
                        )
                    },
                    onAssignNoAgent = {
                        //  assign the agent to the apartment
                        coreVM.onEvent(CoreEvents.UpdateFirestoreField(
                            collectionName = Constants.APARTMENTS_COLLECTION,
                            documentName = clickedApartment?.apartmentName ?: "",
                            subCollectionName = null,
                            subCollectionDocument = null,
                            fieldName = "apartmentAgentAssigned",
                            fieldValue = "",
                            onResponse = { res ->
                                when (res) {
                                    is Response.Success -> {
                                        Toast.makeText(
                                            context,
                                            "Agent removed successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    is Response.Failure -> {}
                                }
                            }
                        ))

                        //  close Assign apartment dialog
                        landlordViewVM.onEvent(
                            AdminLandlordViewEvents.ToggleAlertDialog(
                                isVisible = false,
                                dialogType = AdminLandlordConstants.ASSIGN_AGENT_DIALOG
                            )
                        )
                    },
                    onDismiss = {
                        //  close Assign apartment dialog
                        landlordViewVM.onEvent(
                            AdminLandlordViewEvents.ToggleAlertDialog(
                                isVisible = false,
                                dialogType = AdminLandlordConstants.ASSIGN_AGENT_DIALOG
                            )
                        )
                    }
                )
            }

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
                if (landlordViewVM.apartments.value.isNotEmpty()) {
                    AdminLandlordApartmentsPreview(
                        apartments = landlordViewVM.apartments.value,
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        onAssignClicked = {

                            //  pass clicked apartment name
                            clickedApartment = it

                            //  open Assign apartment dialog
                            landlordViewVM.onEvent(
                                AdminLandlordViewEvents.ToggleAlertDialog(
                                    isVisible = true,
                                    dialogType = AdminLandlordConstants.ASSIGN_AGENT_DIALOG
                                )
                            )
                        }
                    )
                } else {
                    ErrorLottie(
                        lottieImage = R.raw.search_empty,
                        title = null,
                        message = "No Apartments Yet..."
                    )
                }

            }

        }
    }

}