package com.example.houseops_revamped.feature_authentication.presentation.login.domain.model

sealed class LoginFormEvent {

    data class EmailChanged(val email: String) : LoginFormEvent()

    object Submit : LoginFormEvent()
}
