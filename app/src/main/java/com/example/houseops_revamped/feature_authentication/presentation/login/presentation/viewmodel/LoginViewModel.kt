package com.example.houseops_revamped.feature_authentication.presentation.login.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationEvent
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginEvents
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginFormEvent
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginFormState
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.LoginUseCases
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation.LoginValidateUseCases
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.utils.LoginConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCases,
    private val validateUseCases: LoginValidateUseCases
) : ViewModel() {

    var loginResponse by mutableStateOf<Response<*>>(Response.Failure(""))
        private set

    var formState by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

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

            is LoginEvents.PasswordResetEmail -> {
                viewModelScope.launch {
                    useCase.passwordResetEmail(
                        email = event.email,
                        onResponse = {
                            event.onResponse(it)
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

    fun onFormEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        //  validate input
        val emailResult: ValidationResult = validateUseCases.loginValidateEmail.execute(formState.email)

        val hasError = listOf(
            emailResult
        ).any { !it.successful }

        if (hasError) {

            formState = formState.copy(
                emailError = emailResult.errorMessage
            )
            return

        } else {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }

    }
}