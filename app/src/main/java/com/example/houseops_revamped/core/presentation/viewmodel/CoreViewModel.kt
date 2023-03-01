package com.example.houseops_revamped.core.presentation.viewmodel

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.data.datastore.preferences.UserDetailsPreference
import com.example.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.model.events.AlertDialogEvents
import com.example.houseops_revamped.core.domain.use_cases.CoreUseCases
import com.example.houseops_revamped.feature_settings.data.datastore.AccentPreference
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases,
    private val accentPreference: AccentPreference,
    private val userDetailsPreference: UserDetailsPreference
) : ViewModel() {

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

    private val _caretakersList = mutableStateOf<List<Caretaker>>(emptyList())
    val caretakersList: State<List<Caretaker>> = _caretakersList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    //  Bottomsheet
    private val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    private val _bottomSheetData = mutableStateOf<Any?>(null)
    val bottomSheetData: State<Any?> = _bottomSheetData

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

            is CoreEvents.UpdateFirestoreField -> {

                viewModelScope.launch {
                    coreUseCases.updateField(
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        fieldName = event.fieldName,
                        fieldValue = event.fieldValue
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

            is CoreEvents.ChangeAccent -> {
                viewModelScope.launch {
                    accentPreference.setAccent(event.accentColor)
                }
            }

            is CoreEvents.ToggleLoadingCircles -> {
                _isLoading.value = true
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























