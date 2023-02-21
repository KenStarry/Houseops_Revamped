package com.example.houseops_revamped.feature_authentication.sign_up.data.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_authentication.sign_up.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : SignUpRepository {

    override suspend fun createAccount(
        email: String,
        password: String,
        response: (response: Response) -> Unit
    ) {


    }

    override suspend fun createUserCollection(
        user: UsersCollection,
        response: (response: Response) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}









































