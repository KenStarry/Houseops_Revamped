package com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.model

import com.example.houseops_revamped.core.domain.model.Response

sealed class SignUpEvents {

    data class ToggleLoadingCircles(
        val isLoading: Boolean
    ) : SignUpEvents()

    data class CreateAccount(
        val email: String,
        val password: String,
        val response: (response: Response<*>) -> Unit
    ) : SignUpEvents()
}
