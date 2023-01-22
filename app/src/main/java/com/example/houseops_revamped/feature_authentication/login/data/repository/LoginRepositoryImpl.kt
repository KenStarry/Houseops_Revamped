package com.example.houseops_revamped.feature_authentication.login.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response?) -> Unit
    ) {

        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onResponse(Response.Success)
                }
                .addOnFailureListener {
                    onResponse(Response.Failure)
                    Log.d("LOGIN", "Login Failed")
                }

        } catch (e: Exception) {
            onResponse(Response.Failure)
        }
    }
}










