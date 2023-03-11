package com.kenstarry.houseops_revamped.feature_authentication.domain.use_cases

data class ValidateUseCases(
    val validateEmail: ValidateEmail,
    val validateUserName: ValidateUserName,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword
)
