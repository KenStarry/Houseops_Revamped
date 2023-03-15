package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.canopas.lib.showcase.ShowcaseStyle
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.BottomSheet
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.AgentApartmentHouses
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.AgentApartmentTopBar
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet.AddApartmentHouseSheet
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.AgentApartmentConstants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_home.presentation.components.AgentHomeFab

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AgentApartmentView(
    navHostController: NavHostController,
    apartmentName: String,
    primaryColor: Color,
    tertiaryColor: Color
) {


    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.currentUser()
    val context = LocalContext.current
    val agentApartmentVM: AgentApartmentViewModel = hiltViewModel()

    agentApartmentVM.onEvent(
        AgentApartmentEvents.GetApartmentHouses(
            apartmentName = apartmentName,
            onResponse = {}
        ))

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {

        BottomSheet(
            sheetBackground = MaterialTheme.colorScheme.onPrimary,
            sheetContent = { state, scope ->

                when (coreVM.bottomSheetContent.value) {
                    AgentApartmentConstants.ADD_HOUSE_BOTTOM_SHEET -> {
                        AddApartmentHouseSheet(
                            apartmentName = apartmentName,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onDone = { house ->

                                //  submit house to firebase
                                agentApartmentVM.onEvent(AgentApartmentEvents.AddHouse(
                                    apartmentName = apartmentName,
                                    houseModel = house,
                                    onResponse = { res ->
                                        when (res) {
                                            is Response.Success -> {

                                                //  upload images to firestore
                                                coreVM.onEvent(
                                                    CoreEvents.UploadImagesToStorage(
                                                        imageUriList = agentApartmentVM.selectedImagesState.listOfSelectedImages,
                                                        context = context,
                                                        storageRef = "house_images/${house.houseApartmentName}/${house.houseCategory}",
                                                        collectionName = Constants.APARTMENTS_COLLECTION,
                                                        documentName = apartmentName,
                                                        subCollectionName = Constants.HOUSES_SUB_COLLECTION,
                                                        subCollectionDocument = house.houseCategory,
                                                        fieldToUpdate = "houseImageUris",
                                                        onResponse = {}
                                                    )
                                                )

                                                Toast.makeText(
                                                    context,
                                                    "House added successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            is Response.Failure -> {
                                                Toast.makeText(
                                                    context,
                                                    "${res.error}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                ))

                                // close bottomsheet
                                coreVM.onBottomSheetEvent(
                                    BottomSheetEvents.CloseBottomSheet(
                                        state, scope
                                    )
                                )
                            },
                            onCancel = {}
                        )
                    }
                }

            },
            sheetScope = { state, scope ->

                Scaffold(
                    topBar = {
                        AgentApartmentTopBar(
                            title = apartmentName,
                            onBackPressed = { /*TODO*/ },
                            scrollBehavior = scrollBehavior
                        )
                    },

                    floatingActionButton = {
                        AgentHomeFab(
                            primaryColor = primaryColor,
                            onClick = {
                                coreVM.onBottomSheetEvent(
                                    BottomSheetEvents.OpenBottomSheet(
                                        state = state,
                                        scope = scope,
                                        bottomSheetType = AgentApartmentConstants.ADD_HOUSE_BOTTOM_SHEET,
                                        bottomSheetData = null
                                    )
                                )
                            },
                            modifier = Modifier
                                .introShowCaseTarget(
                                    index = 0,
                                    style = ShowcaseStyle.Default.copy(
                                        backgroundColor = tertiaryColor, // specify color of background
                                        backgroundAlpha = 0.8f, // specify transparency of background
                                        targetCircleColor = Color.White.copy(alpha = 0.3f) // specify color of target circle
                                    ),
                                    content = {}
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
                                .padding(16.dp)
                        ) {

                            //  all houses of the apartment
                            AgentApartmentHouses(
                                context = context,
                                houses = agentApartmentVM.apartmentHouses.value,
                                user = coreVM.getUserDetails(currentUser?.email ?: "no email"),
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )

                        }

                    }

                }

            },
            closeBottomSheet = { state, scope -> }
        )

    }

}