package com.example.houseops_revamped.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}























