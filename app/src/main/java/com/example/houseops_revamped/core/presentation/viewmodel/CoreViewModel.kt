package com.example.houseops_revamped.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.use_cases.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
) : ViewModel() {

    var loggedInStatus by mutableStateOf(false)

    fun isUserLoggedIn(): Boolean {

        viewModelScope.launch {
            loggedInStatus = coreUseCases.isUserLoggedIn()
        }

        return loggedInStatus
    }
}