package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.data.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository
import javax.inject.Inject
import kotlin.math.sign

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val gso: GoogleSignInOptions
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

    override suspend fun loginWithGoogle(
        context: Context,
        intent: (intent: Intent) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            val signInIntent = GoogleSignIn.getClient(context, gso).signInIntent

            intent(signInIntent)
            response(Response.Success(signInIntent))

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun firebaseAuthWithGoogle(
        idToken: String,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            val credentials = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credentials)
                .addOnSuccessListener { response(Response.Success(true)) }
                .addOnFailureListener { response(Response.Failure(it)) }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
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










