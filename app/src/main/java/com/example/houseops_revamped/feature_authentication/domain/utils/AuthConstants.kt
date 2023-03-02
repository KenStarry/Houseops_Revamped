package com.example.houseops_revamped.feature_authentication.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.VerifiedUser
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType

object AuthConstants {

    val userTypes = listOf(
        UserType(Icons.Outlined.AdminPanelSettings, "Landlord"),
        UserType(Icons.Outlined.VerifiedUser, "Tenant")
    )

    //  email validation
    const val BLANK_EMAIL_ERROR = "Email cannnot be blank"
    const val INVALID_EMAIL_ERROR = "That's not a valid email"

    //  password validation
    const val PASSWORD_LENGTH = 8

    const val PASSWORD_LENGTH_ERROR =
        "The password needs to contain at least $PASSWORD_LENGTH characters"

    const val PASSWORD_LETTERS_ERROR =
        "The password needs to contain at least one letter and digit"

    const val PASSWORD_MATCH_ERROR = "The passwords don't match"

    const val BLANK_PASSWORD_ERROR = "Password cannot be blank"

    //  username validation
    const val BLANK_USERNAME_ERROR = "Username cannot be blank."
    const val SHORT_USERNAME_ERROR = "Username is too short."
    const val DIGITS_ONLY_USERNAME_ERROR = "Username must have at least one alphabet."

}