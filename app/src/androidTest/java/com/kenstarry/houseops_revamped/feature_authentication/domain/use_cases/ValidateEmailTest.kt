package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

import com.google.common.truth.Truth
import com.kenstarry.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail

    @Before
    fun setup() {
        validateEmail = ValidateEmail()
    }

    @Test
    fun blankEmail_ReturnsFalse() {

        val result: ValidationResult = validateEmail.execute(
            email = ""
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_EMAIL_ERROR
            )
        )
    }

    @Test
    fun emailWithoutAtSymbol_Returns_False() {

        val result: ValidationResult = validateEmail.execute(
            email = "kenny123gmail.com"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        )
    }

    @Test
    fun emailWithoutHost_ReturnsFalse() {

        val result: ValidationResult = validateEmail.execute(
            email = "kenny123@com"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        )
    }

    @Test
    fun emailWithoutDomain_ReturnsFalse() {

        val result: ValidationResult = validateEmail.execute(
            email = "kenny123@gmail"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        )
    }

    @Test
    fun emailWithoutHostAndDomain_ReturnsFalse() {

        val result: ValidationResult = validateEmail.execute(
            email = "kenny123@"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        )
    }

    @Test
    fun emailWithoutNameBodyReturnsFalse() {

        val result: ValidationResult = validateEmail.execute(
            email = "@gmail.com"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        )
    }

    @Test
    fun validEmailAddress_ReturnsTrue() {

        val result: ValidationResult = validateEmail.execute(
            email = "kenny123@gmail.com"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = true,
                errorMessage = null
            )
        )
    }

    @Test
    fun validOrgEmail_ReturnsTrue() {
        val result: ValidationResult = validateEmail.execute(
            email = "sheilla68@organization.org"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = true,
                errorMessage = null
            )
        )
    }

    @Test
    fun validEduEmail_ReturnsTrue() {
        val result: ValidationResult = validateEmail.execute(
            email = "sheilla68@organization.edu"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = true,
                errorMessage = null
            )
        )
    }

    @Test
    fun validAcademicEmail_ReturnsTrue() {
        val result: ValidationResult = validateEmail.execute(
            email = "sheilla68@organization.ac.ke"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = true,
                errorMessage = null
            )
        )
    }

    @Test
    fun validCoEmail_ReturnsTrue() {
        val result: ValidationResult = validateEmail.execute(
            email = "sheilla68@organization.co.ke"
        )

        Truth.assertThat(result).isEqualTo(
            ValidationResult(
                successful = true,
                errorMessage = null
            )
        )
    }
}