package com.example.houseops_revamped.core.domain.repository

import com.google.firebase.auth.FirebaseUser

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser() : FirebaseUser?
}