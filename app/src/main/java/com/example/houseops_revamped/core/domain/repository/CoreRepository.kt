package com.example.houseops_revamped.core.domain.repository

import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.google.firebase.auth.FirebaseUser

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser() : FirebaseUser?

    suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection?) -> Unit
    )

}