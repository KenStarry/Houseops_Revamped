package com.example.houseops_revamped.feature_authentication.domain.use_cases

import com.example.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants

class ValidatePassword {

    fun execute(
        password: String
    ): ValidationResult {

        val passwordContainsLetters = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_PASSWORD_ERROR
            )
        }

        if (password.length < AuthConstants.PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_LENGTH_ERROR
            )
        }

        if (!passwordContainsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_LETTERS_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}