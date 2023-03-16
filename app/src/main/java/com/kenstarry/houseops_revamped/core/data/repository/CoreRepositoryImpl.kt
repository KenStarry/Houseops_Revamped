package com.kenstarry.houseops_revamped.core.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Caretaker
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoreRepository {

    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun currentUser(): FirebaseUser? = auth.currentUser

    override suspend fun sendVerificationEmail(response: (response: Response<*>) -> Unit) {
        try {

            auth.currentUser?.let { user ->
                user.sendEmailVerification()
                    .addOnSuccessListener { response(Response.Success(true)) }
                    .addOnFailureListener { response(Response.Failure(it.message)) }
            }

        } catch (e: Exception) {
            response(Response.Failure(e.message))
        }
    }

    override suspend fun logoutUser(response: (response: Response<*>) -> Unit) {
        try {

            auth.signOut().also { response(Response.Success(true)) }

        } catch (e: Exception) {
            response(Response.Failure(e.message))
        }
    }

    override suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection?) -> Unit
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

    override suspend fun getCaretakerDetails(
        apartmentName: String,
        caretaker: (caretaker: Caretaker?) -> Unit
    ) {
        db.collection(Constants.CARETAKER_COLLECTION)
            .whereEqualTo("caretakerApartment", apartmentName)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {

                    it.toObject(Caretaker::class.java).let { caretaker ->
                        caretaker(caretaker)

                        Log.d("caretaker", "${caretaker.caretakerEmail}")
                    }
                }
            }
            .addOnFailureListener {
                Log.d("caretaker", "$it")
            }
    }

    override suspend fun updateFirestoreField(
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldName: String,
        fieldValue: Any,
        onResponse: (response: Response<*>) -> Unit
    ) {

        try {
            val collectionRef = if (subCollectionName != null
                && subCollectionDocument != null
            ) {
                db.collection(collectionName)
                    .document(documentName)
                    .collection(subCollectionName)
                    .document(subCollectionDocument)
            } else {
                db.collection(collectionName)
                    .document(documentName)
            }

            collectionRef.update(
                fieldName, fieldValue
            ).addOnSuccessListener { onResponse(Response.Success(true)) }
                .addOnFailureListener { onResponse(Response.Failure(it.localizedMessage)) }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun updateUserArrayField(
        collectionName: String,
        documentName: String,
        fieldName: String,
        fieldValue: String,
        isAddItem: Boolean
    ) {
        try {
            val collectionRef = db
                .collection(collectionName)
                .document(documentName)

            if (isAddItem)
                collectionRef.update(
                    fieldName, FieldValue.arrayUnion(fieldValue)
                )
            else
                collectionRef.update(
                    fieldName, FieldValue.arrayRemove(fieldValue)
                )

        } catch (e: Exception) {
            Log.d("update", "$e")
        }
    }

    override suspend fun getAllCaretakers(caretakers: (List<Caretaker>?) -> Unit) {

        try {
            db.collection(Constants.CARETAKER_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    val caretakersList = ArrayList<Caretaker>()

                    querySnapshot?.let {
                        it.forEach { snapshot ->
                            snapshot.toObject(Caretaker::class.java).let { caretaker ->
                                caretakersList.add(caretaker)
                            }
                        }
                    }

                    caretakers(caretakersList)

                }
        } catch (e: Exception) {
            Log.d("caretakers", "$e")
        }
    }

    override suspend fun getApartments(
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    val apartmentsList = mutableListOf<Apartment>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Apartment::class.java)?.let { apartment ->
                            apartmentsList.add(apartment)
                        }
                    }

                    response(Response.Success(apartmentsList))
                    apartments(apartmentsList)

                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun uploadImagesToStorage(
        imageUriList: List<Uri?>,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    ) {

        val ref = FirebaseStorage.getInstance()
            .getReference(storageRef)

        imageUriList.forEach { uri ->

            try {
                uri?.let {

                    val fileRef = ref.child(
                        "${System.currentTimeMillis()}.${getFileExtension(uri, context)}"
                    )

                    fileRef.putFile(it)
                        .addOnSuccessListener {

                            //  Grab the download url
                            try {
                                fileRef.downloadUrl.addOnSuccessListener { url ->
                                    //  add url to the user collection
                                    val userCollection =
                                        if (subCollectionName != null && subCollectionDocument != null)
                                            db.collection(collectionName)
                                                .document(documentName)
                                                .collection(subCollectionName)
                                                .document(subCollectionDocument)
                                        else
                                            db.collection(collectionName)
                                                .document(documentName)

                                    userCollection.update(
                                        fieldToUpdate,
                                        FieldValue.arrayUnion(url)
                                    )

                                    onResponse(Response.Success(url))
                                }
                                    .addOnFailureListener { e ->
                                        onResponse(Response.Failure(e))
                                    }

                            } catch (e: Exception) {
                                //  Return Failure
                                onResponse(Response.Failure(e))
                            }
                        }
                }

            } catch (e: Exception) {
                onResponse(Response.Failure(e))
            }

        }

    }

    override suspend fun uploadSingleImageToStorage(
        uri: Uri?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    ) {

        val ref = FirebaseStorage.getInstance()
            .getReference(storageRef)

        try {
            uri?.let {

                val fileRef = ref.child(
                    "${System.currentTimeMillis()}.${getFileExtension(uri, context)}"
                )

                fileRef.putFile(it)
                    .addOnSuccessListener {

                        //  Grab the download url
                        try {
                            fileRef.downloadUrl.addOnSuccessListener { url ->
                                //  add url to the user collection
                                val myCollection =
                                    if (subCollectionName != null && subCollectionDocument != null)
                                        db.collection(collectionName)
                                            .document(documentName)
                                            .collection(subCollectionName)
                                            .document(subCollectionDocument)
                                    else
                                        db.collection(collectionName)
                                            .document(documentName)

                                myCollection.update(
                                    fieldToUpdate,
                                    url
                                )

                                onResponse(Response.Success(url))
                            }
                                .addOnFailureListener { e ->
                                    onResponse(Response.Failure(e.localizedMessage))
                                }

                        } catch (e: Exception) {
                            //  Return Failure
                            onResponse(Response.Failure(e.localizedMessage))
                        }
                    }
            }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    fun getFileExtension(uri: Uri, context: Context): String? {

        val cr = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()

        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
    }
}

















