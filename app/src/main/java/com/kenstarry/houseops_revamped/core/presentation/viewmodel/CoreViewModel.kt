package com.kenstarry.houseops_revamped.core.presentation.viewmodel

import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.houseops_revamped.core.data.datastore.preferences.UserDetailsPreference
import com.kenstarry.houseops_revamped.core.domain.model.*
import com.kenstarry.houseops_revamped.core.domain.model.events.AlertDialogEvents
import com.kenstarry.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.domain.use_cases.CoreUseCases
import com.kenstarry.houseops_revamped.core.presentation.model.OptionsToggleModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.data.datastore.AccentPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases,
    private val accentPreference: AccentPreference,
    private val userDetailsPreference: UserDetailsPreference,
    private val geocoder: Geocoder
) : ViewModel() {

    //  Places Api
    private val _currentLatLong = mutableStateOf<LatLng?>(null)
    val currentLatLong: State<LatLng?> = _currentLatLong

    //  user current location
    private val _userCurrentLocation = mutableStateOf<Flow<Location>?>(null)
    val userCurrentLocation: State<Flow<Location>?> = _userCurrentLocation

    val primaryAccentFlow: Flow<Int?> get() = accentPreference.getPrimaryAccent
    val tertiaryAccentFlow: Flow<Int?> get() = accentPreference.getTertiaryAccent

    val userTypeFlow: Flow<String?> get() = userDetailsPreference.getUserType

    var loggedInStatus by mutableStateOf(false)
        private set

    var currentUser by mutableStateOf<FirebaseUser?>(null)
        private set

    var user by mutableStateOf<UsersCollection?>(null)
        private set

    var caretaker by mutableStateOf<Caretaker?>(null)
        private set

    private val _allApartments = mutableStateOf<List<Apartment>>(emptyList())
    val allApartments: State<List<Apartment>> = _allApartments

    private val _apartmentDetails = mutableStateOf<Apartment?>(null)
    val apartmentDetails: State<Apartment?> = _apartmentDetails

    private val _allAgents = mutableStateOf<List<UsersCollection>>(emptyList())
    val allAgents: State<List<UsersCollection>> = _allAgents

    private val _caretakersList = mutableStateOf<List<Caretaker>>(emptyList())
    val caretakersList: State<List<Caretaker>> = _caretakersList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _chosenOptionToggle = mutableStateOf<OptionsToggleModel?>(null)
    val chosenOptionToggle: State<OptionsToggleModel?> = _chosenOptionToggle

    private val _alertDialogSelected = mutableStateOf<AlertDialogProperties?>(null)
    val alertDialogSelected: State<AlertDialogProperties?> = _alertDialogSelected

    //  Bottomsheet
    private val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    private val _bottomSheetData = mutableStateOf<Any?>(null)
    var bottomSheetData: State<Any?> = _bottomSheetData

    //  Alert Dialog
    private val _alertDialogContent = mutableStateOf("")
    val alertDialogContent: State<String> = _alertDialogContent

    fun isUserLoggedIn(): Boolean {

        viewModelScope.launch {
            loggedInStatus = coreUseCases.isUserLoggedIn()
        }

        return loggedInStatus
    }

    fun currentUser(): FirebaseUser? {

        viewModelScope.launch {
            currentUser = coreUseCases.currentUser()
        }

        return currentUser
    }

    fun getUserDetails(
        email: String
    ): UsersCollection? {

        viewModelScope.launch {
            coreUseCases.userDetails(
                email = email,
                user = { currentUser ->
                    currentUser?.let {
                        user = it
                    }
                }
            )
        }

        return user
    }

    fun getCaretakerDetails(
        apartment: String
    ): Caretaker? {

        viewModelScope.launch {
            coreUseCases.caretakerDetails(
                apartmentName = apartment,
                caretaker = {
                    caretaker = it
                }
            )
        }

        return caretaker
    }

    fun getAllCaretakers(): List<Caretaker> {
        viewModelScope.launch {
            coreUseCases.getAllCaretakers(
                caretakers = {
                    it?.let { caretakerList ->
                        _caretakersList.value = caretakerList
                    }
                }
            )
        }

        return caretakersList.value
    }

    fun onEvent(event: CoreEvents) {

        when (event) {

            is CoreEvents.GetCurrentLocation -> {
                _userCurrentLocation.value = coreUseCases.getCurrentLocation(
                    interval = event.interval,
                    onResponse = event.onResponse
                )
            }

            is CoreEvents.GetPlaceCoordinates -> {
                viewModelScope.launch {
                    coreUseCases.getPlaceCoordinates(
                        place = event.place,
                        placesClient = event.placesClient,
                        currentLatLong = {
                            _currentLatLong.value = it
                        },
                        response = event.response
                    )
                }
            }

            is CoreEvents.GetLocationAddressName -> {
                viewModelScope.launch {
                    val addresses = geocoder.getFromLocation(
                        event.latLngModel.latitude,
                        event.latLngModel.longitude,
                        1
                    )

                    addresses?.let {
                        //  pass the addresses to our event
                        event.address(
                            LocationAddresses(
                                addressLine = it[0].getAddressLine(0) ?: "",
                                locality = it[0].locality ?: "",
                                subLocality = it[0].subLocality ?: "",
                                country = it[0].countryName ?: ""
                            )
                        )
                    }
                }
            }

            is CoreEvents.SendVerificationEmail -> {
                viewModelScope.launch {
                    coreUseCases.verificationEmail(
                        response = { event.response(it) }
                    )
                }
            }

            is CoreEvents.LogoutUser -> {
                viewModelScope.launch {
                    coreUseCases.logoutUser(
                        response = { event.response(it) }
                    )
                }
            }

            is CoreEvents.GetApartments -> {
                viewModelScope.launch {
                    coreUseCases.getApartments(
                        apartments = {
                            _allApartments.value = it
                        },
                        response = event.response
                    )
                }
            }

            is CoreEvents.GetApartmentDetails -> {
                viewModelScope.launch {
                    coreUseCases.getApartmentDetails(
                        apartmentName = event.apartmentName,
                        apartment = {
                            _apartmentDetails.value = it
                            event.apartmentDetails(it)
                        },
                        response = event.response
                    )
                }
            }

            is CoreEvents.GetAllAgents -> {
                viewModelScope.launch {
                    coreUseCases.getAllAgents(
                        agents = {
                            _allAgents.value = it
                        },
                        response = event.response
                    )
                }
            }

            is CoreEvents.UpdateFirestoreField -> {

                viewModelScope.launch {
                    coreUseCases.updateField(
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        fieldName = event.fieldName,
                        fieldValue = event.fieldValue,
                        onResponse = event.onResponse
                    )
                }
            }

            is CoreEvents.UpdateArrayField -> {

                viewModelScope.launch {
                    coreUseCases.updateArrayField(
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        fieldName = event.fieldName,
                        fieldValue = event.fieldValue,
                        isAddItem = event.isAddItem,
                    )
                }
            }

            is CoreEvents.UploadImagesToStorage -> {

                viewModelScope.launch {
                    coreUseCases.uploadImagesToStorage(
                        imageUriList = event.imageUriList,
                        context = event.context,
                        storageRef = event.storageRef,
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        fieldToUpdate = event.fieldToUpdate,
                        onResponse = { event.onResponse(it) }
                    )
                }
            }

            is CoreEvents.UploadSingleImageToStorage -> {

                viewModelScope.launch {
                    coreUseCases.uploadSingleImageToStorage(
                        uri = event.uri,
                        context = event.context,
                        storageRef = event.storageRef,
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        fieldToUpdate = event.fieldToUpdate,
                        onResponse = { event.onResponse(it) }
                    )
                }
            }

            is CoreEvents.DeleteDocument -> {
                viewModelScope.launch {
                    coreUseCases.deleteDocument(
                        house = event.house,
                        extension = event.extension,
                        imageRefs = event.imageRefs,
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        onResponse = event.onResponse
                    )
                }
            }

            is CoreEvents.ChangeAccent -> {
                viewModelScope.launch {
                    accentPreference.setAccent(event.accentColor)
                }
            }

            is CoreEvents.ToggleLoadingCircles -> {
                _isLoading.value = true
            }

            is CoreEvents.ToggleOptions -> {
                _chosenOptionToggle.value = event.option
            }

            is CoreEvents.ToggleAlertDialog -> {
                _alertDialogSelected.value = AlertDialogProperties(
                    dialogType = event.dialogType,
                    isDialogVisible = event.isDialogVisible
                )
            }

            is CoreEvents.DatastoreSaveUserType -> {
                viewModelScope.launch {
                    userDetailsPreference.setUserType(event.userType)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onBottomSheetEvent(event: BottomSheetEvents<*>) {
        when (event) {

            is BottomSheetEvents.OpenBottomSheet<*> -> {
                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Expanded)
                }

                viewModelScope.launch {
                    _bottomSheetContent.value = event.bottomSheetType
                    _bottomSheetData.value = event.bottomSheetData
                }

                Log.d("bottomsheet", _bottomSheetData.value.toString())
            }

            is BottomSheetEvents.CloseBottomSheet -> {
                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Hidden)
                }
            }

            is BottomSheetEvents.UpdateBottomSheetData -> {
                viewModelScope.launch {
                    _bottomSheetData.value = event.data
                }
            }
        }
    }

    fun onAlertEvent(event: AlertDialogEvents) {
        when (event) {
            is AlertDialogEvents.OpenAlertDialog -> {
                _alertDialogContent.value = event.dialogContent
            }
        }
    }
}























