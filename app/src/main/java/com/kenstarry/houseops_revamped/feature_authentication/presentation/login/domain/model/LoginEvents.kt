package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.model

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kenstarry.houseops_revamped.core.domain.model.Response

sealed class LoginEvents {

    //  user login
    data class Login (
        val email: String,
        val password: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : LoginEvents()

    data class LoginWithGoogle(
        val context: Context,
        val intent: (intent: Intent) -> Unit,
        val response: (response: Response<*>) -> Unit
    ) : LoginEvents()

    data class FirebaseAuthWithGoogle(
        val account: GoogleSignInAccount,
        val shouldCreateCollection: (shouldCreateCollection: Boolean) -> Unit,
        val response: (response: Response<*>) -> Unit
    ) : LoginEvents()

    data class PasswordResetEmail(
        val email: String,
        val onResponse: (res: Response<*>) -> Unit
    ) : LoginEvents()

    data class ToggleAlertDialog(
        val dialogType: String,
        val isDialogVisible: Boolean
    ) : LoginEvents()
}
