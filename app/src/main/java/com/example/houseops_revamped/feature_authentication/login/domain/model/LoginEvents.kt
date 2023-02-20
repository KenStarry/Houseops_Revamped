package com.example.houseops_revamped.feature_authentication.login.domain.model

import com.example.houseops_revamped.core.domain.model.Response

sealed class LoginEvents {

    //  user login
    data class Login    (
        val email: String,
        val password: String,
        val onResponse: (response: Response?) -> Unit
    ) : LoginEvents()

}
