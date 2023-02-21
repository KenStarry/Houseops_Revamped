package com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation

import android.util.Patterns
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants

class LoginValidateEmail {

    fun execute(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_EMAIL_ERROR
            )
        }

        //  check if email is a valid email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}