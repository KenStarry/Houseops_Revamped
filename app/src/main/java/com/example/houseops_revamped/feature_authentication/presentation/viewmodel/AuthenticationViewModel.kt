package com.example.houseops_revamped.feature_authentication.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationEvent
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.example.houseops_revamped.feature_authentication.domain.use_cases.ValidateUseCases
import com.example.houseops_revamped.feature_authentication.presentation.model.RegistrationFormEvent
import com.example.houseops_revamped.feature_authentication.presentation.model.RegistrationFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val useCases: ValidateUseCases
) : ViewModel() {

    var formState by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {

        when (event) {

            is RegistrationFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is RegistrationFormEvent.UserNameChanged -> {
                formState = formState.copy(username = event.username)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                formState = formState.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.ImageUriChanged -> {
                formState = formState.copy(imageUri = event.uri)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        //  validate input
        val emailResult: ValidationResult = useCases.validateEmail.execute(formState.email)
        val usernameResult: ValidationResult = useCases.validateUserName.execute(formState.username)
        val passResult: ValidationResult = useCases.validatePassword.execute(formState.password)
        val repeatedPassResult: ValidationResult =
            useCases.validateRepeatedPassword.execute(
                formState.password, formState.repeatedPassword
            )

        val hasError = listOf(
            emailResult,
            usernameResult,
            passResult,
            repeatedPassResult
        ).any { !it.successful }

        if (hasError) {

            formState = formState.copy(
                emailError = emailResult.errorMessage,
                usernameError = usernameResult.errorMessage,
                passwordError = passResult.errorMessage,
                repeatedPasswordError = repeatedPassResult.errorMessage
            )
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Failure)
            }

        } else {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }

    }
}
























