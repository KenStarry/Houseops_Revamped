package com.example.houseops_revamped.feature_authentication.domain.use_cases

data class ValidateUseCases(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword
)
