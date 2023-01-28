package com.example.houseops_revamped.core.domain.repository

import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.google.firebase.auth.FirebaseUser

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser() : FirebaseUser?

    suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection?) -> Unit
    )

    suspend fun updateFirestoreField(
        collectionName: String,
        documentName: String,
        subCollectionName: String,
        subCollectionDocument: String,
        fieldName: String,
        fieldValue: String
    )

    suspend fun updateUserArrayField(
        collectionName: String,
        documentName: String,
        fieldName: String,
        fieldValue: LikedHouse,
        isAddItem: Boolean
    )

    suspend fun getApartments()

}




























