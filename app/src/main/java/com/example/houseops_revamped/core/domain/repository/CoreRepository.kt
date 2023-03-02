package com.example.houseops_revamped.core.domain.repository

import android.content.Context
import android.net.Uri
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.google.firebase.auth.FirebaseUser

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser() : FirebaseUser?

    suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection?) -> Unit
    )

    suspend fun getCaretakerDetails(
        apartmentName: String,
        caretaker: (caretaker: Caretaker?) -> Unit
    )

    suspend fun getAllCaretakers(
        caretakers: (List<Caretaker>?) -> Unit
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
        fieldValue: String,
        isAddItem: Boolean
    )

    //  upload images to firestore
    suspend fun uploadImagesToStorage(
        imageUriList: List<Uri?>,
        context: Context,
        storageRef: String,
        collectionName: String,
        email: String,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    )

    suspend fun getApartments()

}




























