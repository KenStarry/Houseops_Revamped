package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Response

interface SignUpRepository {

    suspend fun createAccount(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    )

    suspend fun <T> createUserCollection(
        user: T,
        response: (response: Response<*>) -> Unit
    )
}