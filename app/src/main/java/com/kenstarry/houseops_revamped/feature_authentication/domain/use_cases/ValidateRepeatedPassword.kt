package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

import com.kenstarry.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants

class ValidateRepeatedPassword {

    fun execute(
        password: String,
        repeatedPassword: String
    ) : ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_MATCH_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}