package com.example.houseops_revamped.network

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.example.houseops_revamped.models.Constants
import com.example.houseops_revamped.models.UsersCollection
import com.example.houseops_revamped.navigation.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.navigation.HOME_ROUTE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//  create a user account in FirebaseAuth
suspend fun createAccount(
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
                    popUpTo(AUTHENTICATION_ROUTE)
                    launchSingleTop = true
                }

            } else {

                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        }
}

suspend fun createUserCollection(
    userName: String,
    userEmail: String,
    userImageUri: String,
    userPassword: String,
    userIsCaretaker: Boolean
) {

    val user = UsersCollection(
        userName = userName,
        userEmail = userEmail,
        userImageUri = userImageUri,
        userPassword = userPassword,
        userIsCaretaker = userIsCaretaker
    )

    val db: FirebaseFirestore = Firebase.firestore

    //  create users collection
    db.collection(Constants.USERS_COLLECTION).document(userEmail).set(user)
        .addOnSuccessListener {

        }
        .addOnFailureListener {

        }
}

suspend fun loginUser(
    auth: FirebaseAuth,
    context: Context,
    email: String,
    password: String,
    onLoggedInSuccess: () -> Unit
) {

    auth.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()

            onLoggedInSuccess()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
        }

}

suspend fun logoutUser(
    auth: FirebaseAuth,
    context: Context,
    navHostController: NavHostController,
    onLoggedOut: () -> Unit
) {

    //  logout user
    auth.signOut()
    onLoggedOut()
}

















