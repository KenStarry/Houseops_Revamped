package com.example.houseops_revamped.core.data.repository

import android.util.Log
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.repository.CoreRepository
import com.example.houseops_revamped.core.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoreRepository {

    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun currentUser(): FirebaseUser? = auth.currentUser

    override suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection) -> Unit
    ) {

        try {
            db.collection(Constants.USERS_COLLECTION)
                .document(email)
                .addSnapshotListener { snapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    snapshot?.toObject(UsersCollection::class.java)?.let {
                        user(it)
                    }
                }
        } catch (e: Exception) {
            Log.d("userDetails", e.message.toString())
        }

    }
}

















