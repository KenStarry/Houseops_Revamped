package com.example.houseops_revamped.feature_authentication.presentation.model

import android.net.Uri

data class RegistrationFormState(
    val imageUri: Uri? = null,
    val email: String = "",
    val emailError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
)
