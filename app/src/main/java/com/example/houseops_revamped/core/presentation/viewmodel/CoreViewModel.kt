package com.example.houseops_revamped.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.CoreEvents
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.use_cases.CoreUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {

    var loggedInStatus by mutableStateOf(false)
        private set

    var currentUser by mutableStateOf<FirebaseUser?>(null)
        private set

    var user by mutableStateOf<UsersCollection?>(null)
        private set

    var caretaker by mutableStateOf<Caretaker?>(null)
        private set

    var isThumbsUpClicked by mutableStateOf(false)

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
        }
    }
}























