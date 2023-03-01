package com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection

interface SignUpRepository {

    suspend fun createAccount(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    )

    suspend fun createUserCollection(
        user: UsersCollection,
        response: (response: Response<*>) -> Unit
    )
}