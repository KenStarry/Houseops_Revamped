package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.kenstarry.houseops_revamped.feature_authentication.domain.model.ValidationResult
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import org.junit.Before
import org.junit.Test

class ValidateUserNameTest {

    private lateinit var validateUserName: ValidateUserName

    @Before
    fun setup() {
        validateUserName = ValidateUserName()
    }

    @Test
    fun `blank username returns false`() {
        val result = validateUserName.execute("")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.BLANK_USERNAME_ERROR)
        )
    }

    @Test
    fun `short username returns false`() {
        val result = validateUserName.execute("K")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.SHORT_USERNAME_ERROR)
        )
    }

    @Test
    fun `username with digits only returns false`() {
        val result = validateUserName.execute("1234")

        assertThat(result).isEqualTo(
            ValidationResult(false, AuthConstants.DIGITS_ONLY_USERNAME_ERROR)
        )
    }

    @Test
    fun `valid username returns true`() {
        val result = validateUserName.execute("Sheilla")

        assertThat(result).isEqualTo(
            ValidationResult(true, null)
        )
    }
}




































