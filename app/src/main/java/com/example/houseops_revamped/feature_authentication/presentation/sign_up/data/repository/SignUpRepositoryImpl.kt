package com.example.houseops_revamped.feature_authentication.presentation.sign_up.data.repository

import com.example.houseops_revamped.core.domain.model.Landlord
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : SignUpRepository {

    override suspend fun createAccount(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    ) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    response(Response.Success(true))
                }
                .addOnFailureListener { response(Response.Failure(it.message)) }
        } catch (e: Exception) {
            response(Response.Failure(e.message))
        }
    }

    override suspend fun <T> createUserCollection(
        user: T,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            when (user) {
                is UsersCollection -> {

                    //  Login as a tenant
                    user.userEmail?.let { email ->
                        db.collection(Constants.USERS_COLLECTION)
                            .document(email)
                            .set(user)
                            .addOnSuccessListener { response(Response.Success(true)) }
                            .addOnFailureListener { response(Response.Failure(it.message)) }
                    }
                }

                is Landlord -> {

                    //  Login as landlord
                    user.userEmail?.let { email ->
                        db.collection(Constants.USERS_COLLECTION)
                            .document(email)
                            .set(user)
                            .addOnSuccessListener { response(Response.Success(true)) }
                            .addOnFailureListener { response(Response.Failure(it.message)) }
                    }
                }
            }
        } catch (e: FirebaseException) {
            response(Response.Failure(e.message))
        }
    }
}









































