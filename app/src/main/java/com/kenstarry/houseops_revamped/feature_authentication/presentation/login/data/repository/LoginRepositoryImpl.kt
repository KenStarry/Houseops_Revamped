package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    ) {

        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onResponse(Response.Success(true))
                }
                .addOnFailureListener {
                    onResponse(Response.Failure(it))
                    Log.d("LOGIN", "Login Failed")
                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e))
        }
    }

    override suspend fun passwordResetEmail(email: String, onResponse: (res: Response<*>) -> Unit) {

        try {
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    onResponse(Response.Success(true))
                }
                .addOnFailureListener {
                    onResponse(Response.Failure(it))
                }
        } catch (e: Exception) {
            onResponse(Response.Failure(e))
        }
    }

    override suspend fun getUserDetails(email: String, onResponse: (res: Response<*>) -> Unit) {

    }
}










