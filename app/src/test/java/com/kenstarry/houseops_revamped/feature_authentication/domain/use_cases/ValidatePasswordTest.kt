package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.kenstarry.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setup() {
        validatePassword = ValidatePassword()
    }

    //  password should not be blank
    @Test
    fun `blank password returns false`() {
        val result = validatePassword.execute("")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.BLANK_PASSWORD_ERROR)
        )
    }

    //  password should not contain numbers only
    @Test
    fun `password containing digits only returns false`() {
        val result = validatePassword.execute("6787898877")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.DIGITS_ONLY_PASSWORD_ERROR)
        )
    }

    //  password should not contain letters only
    @Test
    fun `password containing letters only returns false`() {
        val result = validatePassword.execute("abcdefffff")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.LETTERS_ONLY_PASSWORD_ERROR)
        )
    }

    //  password must contain an uppercase letter
    @Test
    fun `password not having an uppercase letter returns false`() {
        val result = validatePassword.execute("jhjhjjhgken1234")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.PASSWORD_NO_UPPERCASE_ERROR)
        )
    }

    //  password must start with an uppercase letter
    @Test
    fun `password less than 8 characters returns false`() {
        val result = validatePassword.execute("ken1234")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.PASSWORD_LENGTH_ERROR)
        )
    }
}















