package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

import com.kenstarry.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants

class ValidatePassword {

    fun execute(
        password: String
    ): ValidationResult {

        val isDigitsOnly = password.count { it.isLetter() } == 0
        val isLettersOnly = password.count { it.isDigit() } == 0
        val noUppercaseLetter = password.count { it.isUpperCase() } == 0

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

        //  Password containing digits only
        if (isDigitsOnly)
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.DIGITS_ONLY_PASSWORD_ERROR
            )

        //  Password containing letters only
        if (isLettersOnly)
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.LETTERS_ONLY_PASSWORD_ERROR
            )

        //  check if password doesn't contain at least an uppercase letter
        if(noUppercaseLetter)
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_NO_UPPERCASE_ERROR
            )

        return ValidationResult(
            successful = true
        )
    }
}