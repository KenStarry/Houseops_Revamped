package com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.presentation.viewmodel

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.ApartmentFeature
import com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.model.LndApartmentEvents
import com.example.houseops_revamped.core.domain.model.PlacesAPIResult
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_add_apartment.domain.use_case.AddApartmentUseCases
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LndAddApartmentViewModel @Inject constructor(
    private val useCases: AddApartmentUseCases
) : ViewModel() {

    lateinit var placesClient: PlacesClient

    val locationAutoFill = mutableStateListOf<PlacesAPIResult>()
    val pickedLocation = mutableStateOf("No Location")

    //  bottomsheet type
    var bottomSheetType by mutableStateOf("none")
        private set

    val featureTitle = mutableStateOf("")
    val featureDescription = mutableStateOf("")
    val termsTitle = mutableStateOf("")
    val termsDescription = mutableStateOf("")

    val apartmentName = mutableStateOf("")

    val apartmentPrice = mutableStateOf("")

    val apartmentLocation = mutableStateOf<PlacesAPIResult?>(null)

    val apartmentCaretakerId = mutableStateOf("")

    private val _apartmentFeatures = mutableStateListOf<ApartmentFeature>()
    val apartmentFeatures: SnapshotStateList<ApartmentFeature> = _apartmentFeatures

    private val _termsAndConditions = mutableStateListOf<ApartmentFeature>()
    val termsAndConditions: SnapshotStateList<ApartmentFeature> = _termsAndConditions

    private var job: Job? = null

    @OptIn(ExperimentalMaterialApi::class)
    fun onEvent(event: LndApartmentEvents) {

        when (event) {

            is LndApartmentEvents.AddApartment -> {
                viewModelScope.launch {
                    useCases.addApartment(
                        apartment = event.apartment,
                        response = { event.response(it) }
                    )
                }
            }

            is LndApartmentEvents.SearchPlaces -> {
                job?.cancel()
                locationAutoFill.clear()

                job = viewModelScope.launch {
                    val request = FindAutocompletePredictionsRequest
                        .builder()
                        .setQuery(event.query)
                        .build()

                    placesClient
                        .findAutocompletePredictions(request)
                        .addOnSuccessListener { response ->
                            locationAutoFill += response.autocompletePredictions.map {
                                PlacesAPIResult(
                                    it.getFullText(null).toString(),
                                    it.placeId
                                )
                            }
                        }
                        .addOnFailureListener {
                            Log.d("places", "message : ${it.message}")
                            Log.d("places", "cause : ${it.cause}")
                        }
                }

            }

            is LndApartmentEvents.AddFeature -> {
                apartmentFeatures += event.apartmentFeature
            }

            is LndApartmentEvents.CloseBottomSheet -> {
                //  close bottom sheet
                event.scope.launch {
                    event.state.animateTo(
                        ModalBottomSheetValue.Hidden
                    )
                }
            }

            is LndApartmentEvents.OpenBottomSheet -> {
                //  open bottom sheet
                event.scope.launch {

                    bottomSheetType = event.bottomSheetType

                    event.state.animateTo(
                        ModalBottomSheetValue.Expanded
                    )
                }
            }
        }
    }
}




















