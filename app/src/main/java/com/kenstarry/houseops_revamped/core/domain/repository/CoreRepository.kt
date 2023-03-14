package com.kenstarry.houseops_revamped.core.domain.repository

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.houseops_revamped.core.domain.model.Caretaker
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser() : FirebaseUser?

    suspend fun sendVerificationEmail(
        response: (response: Response<*>) -> Unit
    )

    suspend fun logoutUser(
        response: (response: Response<*>) -> Unit
    )

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
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldName: String,
        fieldValue: Any,
        onResponse: (response: Response<*>) -> Unit
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
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    )

    //  upload single image to firestore
    suspend fun uploadSingleImageToStorage(
        uri: Uri?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    )

    suspend fun getApartments()

}




























