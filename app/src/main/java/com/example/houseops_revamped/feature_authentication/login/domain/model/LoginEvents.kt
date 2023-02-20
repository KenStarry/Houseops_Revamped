package com.example.houseops_revamped.feature_authentication.login.domain.model

import com.example.houseops_revamped.core.domain.model.Response

sealed class LoginEvents {

    //  user login
    data class Login (
        val email: String,
        val password: String,
        val onResponse: (response: Response?) -> Unit
    ) : LoginEvents()

    data class PasswordResetEmail(
        val email: String,
        val onResponse: (res: Response?) -> Unit
    ) : LoginEvents()

    data class ToggleAlertDialog(
        val dialogType: String,
        val isDialogVisible: Boolean
    ) : LoginEvents()
}
