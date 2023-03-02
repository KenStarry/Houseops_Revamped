package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.BuildConfig
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment
import com.example.houseopscaretakers.feature_landlord.core.model.ApartmentFeature
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.LndApartmentMain
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets.FeaturesBottomSheet
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.components.bottomsheets.PlacesBottomSheet
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.utils.LndApartmentConstants
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel.LndAddApartmentViewModel
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model.LndHomeEvents
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel.LndHomeViewModel
import com.google.android.libraries.places.api.Places

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LandlordAddApartment(
    navHostController: NavHostController
) {

    val direction = Direction(navHostController)
    val context = LocalContext.current
    val lndAddApartmentVM: LndAddApartmentViewModel = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()
    val lndHomeVM: LndHomeViewModel = hiltViewModel()

    lndHomeVM.onEvent(
        LndHomeEvents.GetLandlordDetails(
            email = coreVM.currentUser()?.email ?: "no user"
        )
    )
    val landlord = lndHomeVM.landlordDetails.value


    //  initialize places client
    Places.initialize(context, BuildConfig.MAPS_API_KEY)
    lndAddApartmentVM.placesClient = Places.createClient(context)

    BottomSheet(
        sheetContent = { state, scope ->

            when (lndAddApartmentVM.bottomSheetType) {

                LndApartmentConstants.PLACES_BOTTOM_SHEET -> {
                    //  open places bottomsheet
                    PlacesBottomSheet(
                        lndAddApartmentVM = lndAddApartmentVM,
                        onInput = {

                            lndAddApartmentVM.pickedLocation.value = it.address
                            lndAddApartmentVM.apartmentLocation.value = it

                            lndAddApartmentVM.onEvent(
                                LndApartmentEvents.CloseBottomSheet(
                                    state = state,
                                    scope = scope
                                )
                            )
                        }
                    )
                }

                LndApartmentConstants.FEATURES_BOTTOM_SHEET -> {
                    FeaturesBottomSheet(
                        lndAddApartmentVM = lndAddApartmentVM,
                        onDone = { title, description ->

                            //  add the feature to viewmodel
                            lndAddApartmentVM.onEvent(
                                LndApartmentEvents.AddFeature(
                                    apartmentFeature = ApartmentFeature(
                                        title = title,
                                        description = description
                                    )
                                )
                            )

                            lndAddApartmentVM.onEvent(
                                LndApartmentEvents.CloseBottomSheet(
                                    state = state,
                                    scope = scope
                                )
                            )

                            lndAddApartmentVM.featureTitle.value = ""
                            lndAddApartmentVM.featureDescription.value = ""
                        },
                        onCancel = {

                            lndAddApartmentVM.featureTitle.value = ""
                            lndAddApartmentVM.featureDescription.value = ""

                            lndAddApartmentVM.onEvent(
                                LndApartmentEvents.CloseBottomSheet(
                                    state = state,
                                    scope = scope
                                )
                            )
                        }
                    )
                }

                else -> {
                    Column {
                        Text(text = "")
                    }
                }
            }
        },
        sheetScope = { state, scope ->
            LndApartmentMain(
                direction = direction,
                lndAddApartmentVM = lndAddApartmentVM,
                onLocationClicked = {
                    lndAddApartmentVM.onEvent(
                        LndApartmentEvents.OpenBottomSheet(
                            state = state,
                            scope = scope,
                            bottomSheetType = LndApartmentConstants.PLACES_BOTTOM_SHEET
                        )
                    )
                },
                onHouseFeaturesClicked = {
                    lndAddApartmentVM.onEvent(
                        LndApartmentEvents.OpenBottomSheet(
                            state = state,
                            scope = scope,
                            bottomSheetType = LndApartmentConstants.FEATURES_BOTTOM_SHEET
                        )
                    )
                },
                onDone = {
                    lndAddApartmentVM.onEvent(
                        LndApartmentEvents.AddApartment(
                            apartment = Apartment(
                                apartmentLandlordEmail = landlord?.userEmail ?: "no email",
                                apartmentName = lndAddApartmentVM.apartmentName.value,
                                apartmentLocation = lndAddApartmentVM.apartmentLocation.value,
                                apartmentCaretakerId = lndAddApartmentVM.apartmentCaretakerId.value,
                                apartmentFeatures = lndAddApartmentVM.apartmentFeatures
                            ),
                            response = {
                                when (it) {
                                    is Response.Success -> {

                                        Toast.makeText(
                                            context,
                                            "Apartment Added Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        direction.navigateUp()
                                    }
                                    is Response.Failure -> {
                                        Toast.makeText(
                                            context,
                                            "Something went wrong.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        )
                    )
                },
                onCancel = {
                    direction.navigateUp()
                }
            )
        },
        closeBottomSheet = { state, scope ->
            lndAddApartmentVM.onEvent(
                LndApartmentEvents.CloseBottomSheet(
                state = state,
                scope = scope
            ))
        },
        sheetBackground = MaterialTheme.colorScheme.onPrimary
    )



}