package com.example.houseops_revamped.feature_authentication.sign_up.domain.model

import com.example.houseops_revamped.core.domain.model.Response

sealed class SignUpEvents {

    data class createAccount(
        val email: String,
        val password: String,
        val response: (response: Response) -> Unit
    )
}
