package com.kenstarry.houseops_revamped.feature_authentication.presentation.model

import android.net.Uri

sealed class RegistrationFormEvent {

    data class EmailChanged(val email: String) : RegistrationFormEvent()

    data class UserNameChanged(val username: String) : RegistrationFormEvent()

    data class PasswordChanged(val password: String) : RegistrationFormEvent()

    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()

    data class ImageUriChanged(val uri: Uri?) : RegistrationFormEvent()

    object Submit : RegistrationFormEvent()
}
