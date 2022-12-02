package com.example.houseops_revamped.network

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.houseops_revamped.navigation.HOME_ROUTE
import com.google.firebase.auth.FirebaseAuth

//  create a user account in FirebaseAuth
fun createAccount(
    auth: FirebaseAuth,
    context: Context,
    navHostController: NavHostController,
    email: String,
    password: String
) {

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(context, "Account Created Successfully!", Toast.LENGTH_SHORT).show()

                //  navigate to home screen
                navHostController.navigate(HOME_ROUTE) {
                    popUpTo(HOME_ROUTE)
                }

            } else {

                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        }
}