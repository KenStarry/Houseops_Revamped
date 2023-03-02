package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType
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

    private val _chosenUserType = mutableStateOf(AuthConstants.userTypes[1])
    val chosenUserType: State<UserType> = _chosenUserType

    fun onEvent(event: SignUpEvents<Any>) {

        when (event) {
            is SignUpEvents.CreateAccount -> {
                viewModelScope.launch {

                    useCases.createAccount(
                        email = event.email,
                        password = event.password,
                        response = { response ->
                            event.response(response)
                            Log.d("signUp", "$response")
                        }
                    )
                }
            }

            is SignUpEvents.CreateUserCollection -> {
                viewModelScope.launch {
                    useCases.createUserCollection(
                        user = event.user,
                        response = {
                            event.response(it)
                        }
                    )
                }
            }

            is SignUpEvents.ToggleLoadingCircles -> {
                _isLoading.value = event.isLoading
            }

            is SignUpEvents.ToggleUserType -> {
                _chosenUserType.value = event.chosenUserType
            }
        }
    }
}



























