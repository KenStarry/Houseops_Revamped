package com.example.houseops_revamped.network

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

//  query users based on a criteria
suspend fun queryRegisteredCaretakers(
    db: FirebaseFirestore,
    onQuerySuccess: (userList: List<UsersCollection>) -> Unit
) {

    db.collection(Constants.USERS_COLLECTION)
        .whereEqualTo("userHasMadeRequest", true)
        .addSnapshotListener { querySnapshot, error ->

            if (error != null)
                return@addSnapshotListener

            val usersList: ArrayList<UsersCollection> = ArrayList()

            if (querySnapshot != null) {
                for (snapshot in querySnapshot) {

                    val user: UsersCollection = snapshot.toObject()
                    usersList.add(user)
                }
            }

            onQuerySuccess(usersList)
        }
}














