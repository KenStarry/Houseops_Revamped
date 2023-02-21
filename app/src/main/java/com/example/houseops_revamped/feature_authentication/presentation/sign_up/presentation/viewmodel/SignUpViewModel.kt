package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.model.SignUpEvents
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: SignUpUseCases
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun onEvent(event: SignUpEvents) {

        when (event) {
            is SignUpEvents.CreateAccount -> {
                viewModelScope.launch {

                    useCases.createAccount(
                        email = event.email,
                        password = event.password,
                        response = { response ->
                            event.response(response)
                        }
                    )
                }
            }

            is SignUpEvents.ToggleLoadingCircles -> {
                _isLoading.value = event.isLoading
            }
        }
    }
}



























