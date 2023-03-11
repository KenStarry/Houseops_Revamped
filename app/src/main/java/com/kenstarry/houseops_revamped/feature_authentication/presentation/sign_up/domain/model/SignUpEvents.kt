package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.model.UserType

sealed class SignUpEvents<out T> {

    data class ToggleLoadingCircles(
        val isLoading: Boolean
    ) : SignUpEvents<Nothing>()

    data class ToggleUserType(
        val chosenUserType: UserType
    ) : SignUpEvents<Nothing>()

    data class CreateAccount(
        val email: String,
        val password: String,
        val response: (response: Response<*>) -> Unit
    ) : SignUpEvents<Nothing>()

    data class CreateUserCollection<out T>(
        val user: T,
        val response: (response: Response<*>) -> Unit
    ) : SignUpEvents<T>()
}





























