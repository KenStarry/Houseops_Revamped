package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository

import android.content.Context
import android.content.Intent
import com.kenstarry.houseops_revamped.core.domain.model.Response

interface LoginRepository {

    //  login user with email and password
    suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    )

    suspend fun loginWithGoogle(
        context: Context,
        intent: (intent: Intent) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun firebaseAuthWithGoogle(
        idToken: String,
        response: (response: Response<*>) -> Unit
    )

    //  send password reset email
    suspend fun passwordResetEmail(
        email: String,
        onResponse: (res: Response<*>) -> Unit
    )

    //  grab user details from either tenants side or landlord's side
    suspend fun getUserDetails(
        email: String,
        onResponse: (res: Response<*>) -> Unit
    )
}