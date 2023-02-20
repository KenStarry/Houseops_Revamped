package com.example.houseops_revamped.feature_authentication.login.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_authentication.login.domain.model.LoginEvents
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.LoginUseCases
import com.example.houseops_revamped.feature_authentication.login.presentation.utils.LoginConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCases
) : ViewModel() {

    var loginResponse by mutableStateOf<Response>(Response.Loading)
        private set

    private val _isForgotPasswordDialogVisible = mutableStateOf(false)
    val isForgotPasswordDialogVisible: State<Boolean> = _isForgotPasswordDialogVisible

    fun onEvent(event: LoginEvents) {

        when (event) {

            is LoginEvents.Login -> {
                viewModelScope.launch {

                    useCase.login(
                        email = event.email,
                        password = event.password,
                        onResponse = { response ->
                            response?.let { loginResponse = it }
                            event.onResponse(response)
                        }
                    )

                }
            }

            is LoginEvents.ToggleAlertDialog -> {
                when (event.dialogType) {
                    LoginConstants.FORGOT_PASSWORD_DIALOG -> {
                        _isForgotPasswordDialogVisible.value = event.isDialogVisible
                    }
                }
            }

        }
    }
}