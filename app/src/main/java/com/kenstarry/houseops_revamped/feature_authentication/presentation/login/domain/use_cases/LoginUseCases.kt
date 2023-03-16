package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases

data class LoginUseCases(
    val login: Login,
    val loginWithGoogle: LoginWithGoogle,
    val firebaseAuthWithGoogle: FirebaseAuthWithGoogle,
    val passwordResetEmail: PasswordResetEmail
)
