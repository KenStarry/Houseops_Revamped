package com.kenstarry.houseops_revamped.core.domain.repository

import android.content.Context
import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.houseops_revamped.core.domain.model.*

interface CoreRepository {

    suspend fun getPlaceCoordinates(
        place: PlacesAPIResult,
        placesClient: PlacesClient,
        currentLatLong: (currentLatLong: LatLng) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser(): FirebaseUser?

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

    suspend fun getAllAgents(
        agents: (agents: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun getApartments(
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun getApartmentDetails(
        apartmentName: String,
        apartment: (apartment: Apartment) -> Unit,
        response: (response: Response<*>) -> Unit
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
        imageUriList: List<ImageModel?>,
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
        uri: ImageModel?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    )

    //  delete a document from firestore
    suspend fun deleteDocument(
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        onResponse: (response: Response<*>) -> Unit
    )

}




























