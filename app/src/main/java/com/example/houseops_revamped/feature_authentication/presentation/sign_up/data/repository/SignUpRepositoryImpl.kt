package com.example.houseops_revamped.feature_authentication.presentation.sign_up.data.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository
import com.google.firebase.FirebaseException
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
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { Response.Success }
                .addOnFailureListener { Response.Failure(it) }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun createUserCollection(
        user: UsersCollection,
        response: (response: Response) -> Unit
    ) {
        try {
            user.userEmail?.let { email ->
                db.collection(Constants.USERS_COLLECTION)
                    .document(email)
                    .set(user)
                    .addOnSuccessListener { Response.Success }
                    .addOnFailureListener { Response.Failure(it) }
            }
        } catch (e: FirebaseException) {
            Response.Failure(e)
        }
    }
}









































