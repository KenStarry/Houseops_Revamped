package com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository

class PasswordResetEmail(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(
        email: String,
        onResponse: (res: Response<*>) -> Unit
    ) = repository.passwordResetEmail(
        email = email,
        onResponse = {
            onResponse(it)
        }
    )
}