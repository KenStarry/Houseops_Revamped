package com.example.houseops_revamped.feature_authentication.presentation.login.domain.repository

import com.example.houseops_revamped.core.domain.model.Response

interface LoginRepository {

    //  login user with email and password
    suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response?) -> Unit
    )

    //  send password reset email
    suspend fun passwordResetEmail(
        email: String,
        onResponse: (res: Response?) -> Unit
    )
}