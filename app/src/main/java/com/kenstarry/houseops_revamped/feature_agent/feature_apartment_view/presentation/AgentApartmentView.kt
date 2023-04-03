package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.canopas.lib.showcase.ShowcaseStyle
import com.kenstarry.houseops_revamped.R
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
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.presentation.components.ErrorLottie
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.DeleteHouseDialog
import java.text.NumberFormat

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

    var selectedHouse by remember {
        mutableStateOf<HouseModel?>(null)
    }
    var houseToDelete by remember {
        mutableStateOf<HouseModel?>(null)
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
                            house = selectedHouse,
                            apartmentName = apartmentName,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onDone = { house ->

                                val randomNum = "${(0..9).random()}" +
                                        "${(0..9).random()}" +
                                        "${(0..9).random()}"

                                val houseWithId = house.copy(
                                    houseId = "${apartmentName.take(2)}-${agentApartmentVM.selectedHouseCategory.value}-$randomNum"
                                )

                                //  submit house to firebase
                                agentApartmentVM.onEvent(AgentApartmentEvents.AddHouse(
                                    apartmentName = apartmentName,
                                    houseModel = houseWithId,
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
                            onUpdate = { house ->

                                val newHouseModel = house.copy(
                                    houseCategory = agentApartmentVM.selectedHouseCategory.value,
                                    houseUnits = agentApartmentVM.selectedVacantUnits.value.toString(),
                                    houseFeatures = agentApartmentVM.selectedFeaturesState.listOfSelectedFeatures.map { it.title },
                                    housePrice = agentApartmentVM.selectedHousePrice.value,
                                    houseDescription = agentApartmentVM.selectedHouseDescription.value
                                )

                                //  submit house to firebase
                                agentApartmentVM.onEvent(AgentApartmentEvents.UpdateHouse(
                                    apartmentName = apartmentName,
                                    houseModel = newHouseModel,
                                    onResponse = { res ->
                                        when (res) {
                                            is Response.Success -> {
                                                Toast.makeText(
                                                    context,
                                                    "House updated successfully",
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
                                selectedHouse = null
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

                    //  on delete dialog
                    AnimatedVisibility(
                        visible = coreVM.alertDialogSelected.value?.dialogType
                                == Constants.AGENT_DELETE_HOUSE_ALERT_DIALOG &&
                                coreVM.alertDialogSelected.value?.isDialogVisible
                                == true
                    ) {

                        //  show delete confirmation
                        DeleteHouseDialog(
                            house = houseToDelete,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            onConfirm = {

                                //  delete house
                                houseToDelete?.let {
                                    coreVM.onEvent(CoreEvents.DeleteDocument(
                                        house = it,
                                        extension = "jpg",
                                        imageRefs = it.houseImageUris,
                                        collectionName = Constants.APARTMENTS_COLLECTION,
                                        documentName = apartmentName,
                                        subCollectionName = Constants.HOUSES_SUB_COLLECTION,
                                        subCollectionDocument = it.houseCategory,
                                        onResponse = { res ->
                                            when (res) {
                                                is Response.Success -> {
                                                    Toast.makeText(
                                                        context,
                                                        "House Deleted successfully.",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                        .show()
                                                }
                                                is Response.Failure -> {
                                                    Toast.makeText(
                                                        context,
                                                        res.error.toString(),
                                                        Toast.LENGTH_SHORT
                                                    )
                                                        .show()
                                                }
                                            }
                                        }
                                    ))
                                }

                                coreVM.onEvent(
                                    CoreEvents.ToggleAlertDialog(
                                        dialogType = Constants.AGENT_DELETE_HOUSE_ALERT_DIALOG,
                                        isDialogVisible = false
                                    )
                                )
                            },
                            onDismiss = {
                                coreVM.onEvent(
                                    CoreEvents.ToggleAlertDialog(
                                        dialogType = Constants.AGENT_DELETE_HOUSE_ALERT_DIALOG,
                                        isDialogVisible = false
                                    )
                                )
                            }
                        )

                    }

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
                            if (agentApartmentVM.apartmentHouses.value.isNotEmpty()) {

                                AgentApartmentHouses(
                                    context = context,
                                    houses = agentApartmentVM.apartmentHouses.value,
                                    user = coreVM.getUserDetails(currentUser?.email ?: "no email"),
                                    primaryColor = primaryColor,
                                    tertiaryColor = tertiaryColor,
                                    onDelete = { house ->
                                        //  pass house to variable
                                        houseToDelete = house
                                        coreVM.onEvent(
                                            CoreEvents.ToggleAlertDialog(
                                                Constants.AGENT_DELETE_HOUSE_ALERT_DIALOG,
                                                true
                                            )
                                        )
                                    },
                                    onUpdate = {

                                        selectedHouse = it

                                        coreVM.onBottomSheetEvent(
                                            BottomSheetEvents.OpenBottomSheet(
                                                state = state,
                                                scope = scope,
                                                bottomSheetType = AgentApartmentConstants.ADD_HOUSE_BOTTOM_SHEET,
                                                bottomSheetData = null
                                            )
                                        )
                                    }
                                )

                            } else {
                                ErrorLottie(
                                    lottieImage = R.raw.search_empty,
                                    title = null,
                                    message = "Add Houses to see them here."
                                )
                            }

                        }

                    }

                }

            },
            closeBottomSheet = { state, scope ->
                // close bottomsheet
                coreVM.onBottomSheetEvent(
                    BottomSheetEvents.CloseBottomSheet(
                        state, scope
                    )
                )
            }
        )

    }

}