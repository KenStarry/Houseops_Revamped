package com.example.houseops_revamped.core.data.repository

import com.example.houseops_revamped.core.domain.repository.CoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : CoreRepository {

    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun currentUser(): FirebaseUser? = auth.currentUser
}